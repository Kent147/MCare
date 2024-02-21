package vn.mcare.system.repository.intface;

import org.springframework.stereotype.Component;
import vn.mcare.system.common.pojo.dto.MedicalReportDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.MedicalReportRecord;

@Component
public interface MedicalReportRepo extends IBaseRepo<String, MedicalReportRecord> {
  MedicalReportDto getByCif(String cif);
}
