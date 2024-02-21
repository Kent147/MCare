package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.dto.DataListDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.DataListRepo;
import vn.mcare.system.repository.model.tables.DataList;
import vn.mcare.system.repository.model.tables.records.DataListRecord;

@Slf4j
@Repository
public class DataListRepoImpl extends BaseRepoImpl<Integer, DataListRecord, DataList> implements
    DataListRepo {

  @Override
  public List<DataListDto> getDataListByKey(String key) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      DataList dl = DataList.DATA_LIST;

      return context
          .selectFrom(dl)
          .where(dl.DATA_KEY.eq(key))
          .fetchInto(DataListDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
