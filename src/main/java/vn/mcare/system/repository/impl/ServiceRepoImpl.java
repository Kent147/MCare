package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SelectWhereStep;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.api.input.GetAllServiceOutput;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.dto.ServiceDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.ServiceRepo;
import vn.mcare.system.repository.model.tables.DataList;
import vn.mcare.system.repository.model.tables.Service;
import vn.mcare.system.repository.model.tables.records.ServiceRecord;

@Repository
@Slf4j
public class ServiceRepoImpl extends BaseRepoImpl<String, ServiceRecord, Service> implements ServiceRepo {

  @Override
  public GetAllServiceOutput getAll(SearchInput search) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Service service = Service.SERVICE;
      DataList dataList = DataList.DATA_LIST;

      SelectWhereStep query = context
              .select(
                      service.SERVICE_ID,
                      service.SERVICE_NAME,
                      service.PRICE,
                      service.DESCRIPTION,
                      service.CREATED_AT,
                      service.MODIFIED_AT)
              .from(service);

      if (search.getSearch() != null) {
        query.where(service.SERVICE_NAME.likeIgnoreCase(search.getSearch() + "%"));
      }

      switch (search.getSort()) {
        case "serviceName":
          if (search.getAsc()) {
            query.orderBy(service.SERVICE_NAME.asc());
          } else {
            query.orderBy(service.SERVICE_NAME.desc());
          }
          break;
        default:
          query.orderBy(service.MODIFIED_AT.desc());
      }

      Integer pageIndex = search.getPage();

      Integer pageCount = Math.round(count(query.asTable()) / search.getLimit());

      if (pageIndex <= 0) {
        pageIndex = 1;
      }

      List<ServiceDto> list = query
              .limit(search.getLimit())
              .offset((pageIndex - 1) * search.getLimit())
              .fetchInto(ServiceDto.class);

      return new GetAllServiceOutput()
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
  public ServiceDto getById(String serviceId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      return context
              .selectFrom(Service.SERVICE)
              .where(Service.SERVICE.SERVICE_ID.eq(serviceId))
              .fetchInto(ServiceDto.class)
              .get(0);
    } catch (IndexOutOfBoundsException e) {
      log.error("Service not found");
      throw new SQLException.RecordNotFound("Service not found");
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<ServiceDto> searchByName(String serviceName) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      return context
              .selectFrom(Service.SERVICE)
              .where(Service.SERVICE.SERVICE_NAME.likeIgnoreCase(serviceName + "%"))
              .fetchInto(ServiceDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
