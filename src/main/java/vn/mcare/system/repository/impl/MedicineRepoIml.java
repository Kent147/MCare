package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SelectWhereStep;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EDataList;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.api.output.GetAllMedicineOutput;
import vn.mcare.system.common.pojo.dto.MedForSaleDto;
import vn.mcare.system.common.pojo.dto.MedicineDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.MedicineRepo;
import vn.mcare.system.repository.model.tables.DataList;
import vn.mcare.system.repository.model.tables.Inventory;
import vn.mcare.system.repository.model.tables.Medicine;
import vn.mcare.system.repository.model.tables.records.MedicineRecord;

@Repository
@Slf4j
public class MedicineRepoIml extends BaseRepoImpl<String, MedicineRecord, Medicine> implements MedicineRepo {


  @Override
  public GetAllMedicineOutput getAll(SearchInput search) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Medicine med = Medicine.MEDICINE;
      DataList dataList = DataList.DATA_LIST;

      SelectWhereStep query = context
              .select(
                      med.MEDICINE_ID,
                      med.MEDICINE_NAME,
                      med.SOURCE.as("source"),
                      dataList.LABEL.as("sourceName"),
                      med.CREATED_AT,
                      med.MODIFIED_AT)
              .from(med)
              .leftJoin(dataList)
              .on(med.SOURCE.eq(dataList.DATA_VALUE));

      //condition
      query.where(dataList.DATA_KEY.eq(EDataList.COUNTRY.getKey()));

      if (search.getSearch() != null) {
        query.where(med.MEDICINE_NAME.likeIgnoreCase(search.getSearch() + "%"));
      }

      switch (search.getSort()) {
        case "medicineName":
          if (search.getAsc()) {
            query.orderBy(med.MEDICINE_NAME.asc());
          } else {
            query.orderBy(med.MEDICINE_NAME.desc());
          }
          break;
        default:
          query.orderBy(med.MODIFIED_AT.desc());
      }

      Integer pageIndex = search.getPage();

      Integer pageCount = Math.round(count(query.asTable()) / search.getLimit());

      if (pageIndex <= 0) {
        pageIndex = 1;
      }

      List<MedicineDto> list = query
              .limit(search.getLimit())
              .offset((pageIndex - 1) * search.getLimit())
              .fetchInto(MedicineDto.class);

      return new GetAllMedicineOutput()
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
  public void checkValidMedicine(String medicineId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      context.selectFrom(Medicine.MEDICINE).where(Medicine.MEDICINE.MEDICINE_ID.eq(medicineId)).fetch().get(0);
      throw new SQLException.RecordExisted("Medicine existed");
    } catch (IndexOutOfBoundsException e) {
      log.debug("Medicine can use");
    } catch (SQLException.RecordExisted e) {
      log.error(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public MedicineDto getMedicineById(String medicineId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      return context
              .selectFrom(Medicine.MEDICINE)
              .where(Medicine.MEDICINE.MEDICINE_ID.eq(medicineId))
              .fetchInto(MedicineDto.class)
              .get(0);
    } catch (IndexOutOfBoundsException e) {
      log.error("Medicine not found");
      throw new SQLException.RecordNotFound("Medicine not found");
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<MedicineDto> findMedicineByName(String medicineName) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      return context
              .selectFrom(Medicine.MEDICINE)
              .where(Medicine.MEDICINE.MEDICINE_NAME.like(medicineName + "%"))
              .fetchInto(MedicineDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<MedForSaleDto> findMedForSell(String name) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Medicine med = Medicine.MEDICINE;
      Inventory inv = Inventory.INVENTORY;
      DataList dataList = DataList.DATA_LIST;

      return context
              .select(
                      inv.MEDICINE_ID,
                      inv.SELL_PRICE,
                      inv.LOT_ID,
                      inv.INVENTORY_ID,
                      med.MEDICINE_NAME,
                      inv.AMOUNT,
                      dataList.LABEL.as("calUnitName"),
                      inv.CAL_UNIT)
              .from(inv)
              .leftJoin(med)
              .on(inv.MEDICINE_ID.eq(med.MEDICINE_ID))
              .leftJoin(dataList)
              .on(dataList.DATA_VALUE.eq(inv.CAL_UNIT.cast(String.class)))
              .where(med.MEDICINE_NAME.like(name + "%"))
              .and(dataList.DATA_KEY.eq(EDataList.CAL_UNIT.getKey()))
              .fetchInto(MedForSaleDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

}
