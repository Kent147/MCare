package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.dto.HealthMaintenanceDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.HealthMaintenanceRecord;

public interface HealthMaintenanceRepo extends IBaseRepo<Integer, HealthMaintenanceRecord> {

    List<HealthMaintenanceDto> getByExamineId(String examineId);
}
