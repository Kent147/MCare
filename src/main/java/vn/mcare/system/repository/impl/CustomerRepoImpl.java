package vn.mcare.system.repository.impl;

import static org.jooq.impl.DSL.concat;
import static org.jooq.impl.DSL.val;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SelectWhereStep;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.api.output.GetAllCustomerOutput;
import vn.mcare.system.common.pojo.api.output.GetCustomerOutput;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.CustomerRepo;
import vn.mcare.system.repository.model.tables.Customer;
import vn.mcare.system.repository.model.tables.DataList;
import vn.mcare.system.repository.model.tables.District;
import vn.mcare.system.repository.model.tables.Province;
import vn.mcare.system.repository.model.tables.Ward;
import vn.mcare.system.repository.model.tables.records.CustomerRecord;

@Repository
@Slf4j
public class CustomerRepoImpl extends BaseRepoImpl<String, CustomerRecord, Customer>
        implements CustomerRepo {

  @Override
  public GetAllCustomerOutput getAll(SearchInput search) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Customer cus = Customer.CUSTOMER;
      DataList dl = DataList.DATA_LIST;
      Ward ward = Ward.WARD;
      Province prov = Province.PROVINCE;
      District dist = District.DISTRICT;

      SelectWhereStep query =
              context
                      .select(
                              cus.CIF,
                              cus.FULL_NAME,
                              cus.GENDER,
                              cus.BOD,
                              cus.CARD_ID,
                              cus.PHONE,
                              cus.EMAIL,
                              dl.LABEL.as("nation"),
                              cus.CAREER,
                              cus.STREET,
                              prov.NAME.as("province"),
                              cus.PROVINCE.as("provinceId"),
                              concat(ward.PREFIX, val(" "), ward.NAME).as("ward"),
                              concat(dist.PREFIX, val(" "), dist.NAME).as("district"),
                              cus.DISTRICT.as("districtId"),
                              cus.WARD.as("wardId"),
                              prov.NAME,
                              cus.DESCRIPTION)
                      .from(cus)
                      .leftJoin(dl)
                      .on(cus.NATION.equalIgnoreCase(dl.DATA_VALUE))
                      .and(dl.DATA_KEY.equalIgnoreCase("NATION"))
                      .leftJoin(ward)
                      .on(cus.WARD.eq(ward.ID))
                      .leftJoin(prov)
                      .on(cus.PROVINCE.eq(prov.ID))
                      .leftJoin(dist)
                      .on(cus.DISTRICT.eq(dist.ID));

      if (search.getSearch() != null) {
        query.where(cus.FULL_NAME.likeIgnoreCase(search.getSearch() + "%"));
      }

      switch (search.getSort()) {
        case "fullName":
          if (search.getAsc()) {
            query.orderBy(cus.FULL_NAME.asc());
          } else {
            query.orderBy(cus.FULL_NAME.desc());
          }
        default:
          query.orderBy(cus.MODIFIED_AT.desc());
      }

      Integer pageIndex = search.getPage();

      if (pageIndex <= 0) {
        pageIndex = 1;
      }

      Integer pageCount = Math.round(count(query.asTable()) / search.getLimit());


      List<GetCustomerOutput> list = query
              .limit(search.getLimit())
              .offset((pageIndex - 1) * search.getLimit())
              .fetchInto(GetCustomerOutput.class);

      return new GetAllCustomerOutput()
              .setLimit(search.getLimit())
              .setPageCount(pageCount)
              .setPage(search.getPage())
              .setList(list);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public void checkValidCardId(String cardId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      context
              .selectFrom(Customer.CUSTOMER)
              .where(Customer.CUSTOMER.CARD_ID.like(cardId))
              .fetch()
              .get(0);
      throw new SQLException.RecordExisted("CardId existed");
    } catch (IndexOutOfBoundsException e) {
      log.info("CardId can use");
    } catch (SQLException.RecordExisted e) {
      log.error(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public GetCustomerOutput getByCif(String cif) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Customer cus = Customer.CUSTOMER;
      DataList dl = DataList.DATA_LIST;
      Ward ward = Ward.WARD;
      Province prov = Province.PROVINCE;
      District dist = District.DISTRICT;

      return context
              .select(
                      cus.CIF,
                      cus.FULL_NAME,
                      cus.GENDER,
                      cus.BOD,
                      cus.CARD_ID,
                      cus.PHONE,
                      cus.EMAIL,
                      dl.LABEL.as("nation"),
                      cus.CAREER,
                      cus.STREET,
                      prov.NAME.as("province"),
                      concat(ward.PREFIX, val(" "), ward.NAME).as("ward"),
                      concat(dist.PREFIX, val(" "), dist.NAME).as("district"),
                      prov.NAME,
                      cus.DESCRIPTION)
              .from(cus)
              .leftJoin(dl)
              .on(cus.NATION.equalIgnoreCase(dl.DATA_VALUE))
              .and(dl.DATA_KEY.equalIgnoreCase("NATION"))
              .leftJoin(ward)
              .on(cus.WARD.eq(ward.ID))
              .leftJoin(prov)
              .on(cus.PROVINCE.eq(prov.ID))
              .leftJoin(dist)
              .on(cus.DISTRICT.eq(dist.ID))
              .where(cus.CIF.eq(cif))
              .fetchOneInto(GetCustomerOutput.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<GetCustomerOutput> SearchByName(String customerName) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context
              .selectFrom(Customer.CUSTOMER)
              .where(Customer.CUSTOMER.FULL_NAME.like(customerName + "%"))
              .fetchInto(GetCustomerOutput.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
