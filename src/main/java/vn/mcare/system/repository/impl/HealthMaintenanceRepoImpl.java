package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.dto.HealthMaintenanceDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.HealthMaintenanceRepo;
import vn.mcare.system.repository.model.tables.HealthMaintenance;
import vn.mcare.system.repository.model.tables.records.HealthMaintenanceRecord;

@Slf4j
@Repository
public class HealthMaintenanceRepoImpl extends BaseRepoImpl<Integer, HealthMaintenanceRecord, HealthMaintenance> implements HealthMaintenanceRepo {

  @Override
  public List<HealthMaintenanceDto> getByExamineId(String examineId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      HealthMaintenance health = HealthMaintenance.HEALTH_MAINTENANCE;
      return context
              .selectFrom(health)
              .where(health.EXAMINE_ID.eq(examineId))
              .fetchInto(HealthMaintenanceDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
