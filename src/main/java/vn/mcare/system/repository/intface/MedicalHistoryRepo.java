package vn.mcare.system.repository.intface;

import java.util.List;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.pojo.api.output.MedicalHistoryDetail;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.MedicalHistoryRecord;

@Component
public interface MedicalHistoryRepo extends IBaseRepo<Integer, MedicalHistoryRecord> {

  List<MedicalHistoryDetail> getMedicalHistoryByCif(String cif);

}
