package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.dto.PrescriptionDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.PrescriptionRecord;

public interface PrescriptionRepo extends IBaseRepo<Integer, PrescriptionRecord> {

    List<PrescriptionDto> getByExamineId(String examineId);
}
