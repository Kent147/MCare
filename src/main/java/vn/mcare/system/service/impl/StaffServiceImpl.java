package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EMedicalExamineStatus;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.helper.DateHelper;
import vn.mcare.system.common.pojo.api.input.GetReceiveCustomerListInput;
import vn.mcare.system.common.pojo.api.input.ReceiveCustomerInput;
import vn.mcare.system.common.pojo.api.output.ReceiveCustomerListOutput;
import vn.mcare.system.common.pojo.dto.ExamineTodayDto;
import vn.mcare.system.common.pojo.dto.MedicalReportDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.common.tool.Generator;
import vn.mcare.system.repository.intface.ExamineInfoRepo;
import vn.mcare.system.repository.intface.MedicalReportDetailRepo;
import vn.mcare.system.repository.intface.MedicalReportRepo;
import vn.mcare.system.repository.model.tables.records.ExamineInfoRecord;
import vn.mcare.system.repository.model.tables.records.MedicalReportDetailRecord;
import vn.mcare.system.service.intface.StaffService;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StaffServiceImpl implements StaffService {

  private final Gson gson;
  private final ExamineInfoRepo examineInfoRepo;
  private final MedicalReportRepo medicalReportRepo;
  private final MedicalReportDetailRepo medicalReportDetailRepo;

  @Override
  public RestfulCommonResponse getReceiveCustomerList(JsonObject payload) {
    try {
      GetReceiveCustomerListInput input = gson.fromJson(payload, GetReceiveCustomerListInput.class);
      ReceiveCustomerListOutput output = examineInfoRepo.getReceiveCustomerListOutput(input);
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse receiveCustomer(JsonObject payload) {
    try {
      ReceiveCustomerInput input = gson.fromJson(payload, ReceiveCustomerInput.class);
      LocalDateTime now = LocalDateTime.now();
      MedicalReportDto reportDto = medicalReportRepo.getByCif(input.getCif());
      if (reportDto == null) {
        throw new SQLException.RecordNotFound("Cif not have medical report");
      }

      String examineId = Generator.generate();
      MedicalReportDetailRecord medicalDetailRecord = new MedicalReportDetailRecord();
      medicalDetailRecord
              .setMedicalReportId(reportDto.getMedicalReportId())
              .setExamineId(examineId)
              .setCreatedAt(now)
              .setStatus(EMedicalExamineStatus.PENDING.getCode())
              .setModifiedAt(now);

      medicalReportDetailRepo.save(medicalDetailRecord);

      ExamineInfoRecord examineRecord = new ExamineInfoRecord();
      examineRecord
              .setExamineId(examineId)
              .setStatus(input.getStatus())
              .setCheckIn(DateHelper.toLocalDateTime(input.getCheckIn()))
              .setCreatedAt(now)
              .setModifiedAt(now);

      examineInfoRepo.save(examineRecord);

      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (SQLException.RecordNotFound e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getExamineToday() {
    try {
      List<ExamineTodayDto> output = examineInfoRepo.getExamineToday();
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }
}
