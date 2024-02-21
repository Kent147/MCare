package vn.mcare.system.repository.intface;

import java.time.LocalDateTime;
import java.util.List;
import vn.mcare.system.common.pojo.api.output.RegisterExaminationOutput;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.RegisterExaminationRecord;

public interface RegisterExaminationRepo extends IBaseRepo<Integer, RegisterExaminationRecord> {

  Boolean checkExistRandomNumber(String randomNumber);

  LocalDateTime getNextDate();

  List<RegisterExaminationOutput> getAll();
}
