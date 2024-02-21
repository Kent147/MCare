package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.api.input.GetReceiveCustomerListInput;
import vn.mcare.system.common.pojo.api.output.ReceiveCustomerListOutput;
import vn.mcare.system.common.pojo.dto.ExamineDetailDto;
import vn.mcare.system.common.pojo.dto.ExamineDoneDto;
import vn.mcare.system.common.pojo.dto.ExamineDto;
import vn.mcare.system.common.pojo.dto.ExamineTodayDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.ExamineInfoRecord;

public interface ExamineInfoRepo extends IBaseRepo<String, ExamineInfoRecord> {
  List<ExamineDto> getByMedicalReport(String medicalReportId);

  ExamineDetailDto getExaminingDetail(String medicalReportId);

  ReceiveCustomerListOutput getReceiveCustomerListOutput(GetReceiveCustomerListInput input);

  ExamineDto getLastExamineByCif(String cif);

  List<ExamineTodayDto> getExamineToday();

  List<ExamineDoneDto> getExamineDoneByCif(String cif);
}
