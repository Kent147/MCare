package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EDataList;
import vn.mcare.system.common.constant.enums.EExamineStatus;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.exception.MedicalReportException.ExamineStatusInvalid;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.exception.SQLException.RecordNotFound;
import vn.mcare.system.common.helper.validation.ChangeExamineStatusInput;
import vn.mcare.system.common.pojo.api.input.CreateExamineInput;
import vn.mcare.system.common.pojo.api.input.CreateHealthMaintenanceInput;
import vn.mcare.system.common.pojo.api.input.CreatePrescriptionInput;
import vn.mcare.system.common.pojo.api.input.UpdateExamineInput;
import vn.mcare.system.common.pojo.api.input.UpdateHealthMaintenanceInput;
import vn.mcare.system.common.pojo.api.input.UpdatePrescriptionInput;
import vn.mcare.system.common.pojo.dto.DataListDto;
import vn.mcare.system.common.pojo.dto.ExamineDetailDto;
import vn.mcare.system.common.pojo.dto.ExamineDoneDto;
import vn.mcare.system.common.pojo.dto.ExamineDto;
import vn.mcare.system.common.pojo.dto.HealthMaintenanceDto;
import vn.mcare.system.common.pojo.dto.MedicalReportDto;
import vn.mcare.system.common.pojo.dto.PrescriptionDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.common.tool.Generator;
import vn.mcare.system.repository.intface.DataListRepo;
import vn.mcare.system.repository.intface.ExamineInfoRepo;
import vn.mcare.system.repository.intface.HealthMaintenanceRepo;
import vn.mcare.system.repository.intface.MedicalHistoryRepo;
import vn.mcare.system.repository.intface.MedicalReportDetailRepo;
import vn.mcare.system.repository.intface.MedicalReportRepo;
import vn.mcare.system.repository.intface.PrescriptionRepo;
import vn.mcare.system.repository.model.tables.records.ExamineInfoRecord;
import vn.mcare.system.repository.model.tables.records.HealthMaintenanceRecord;
import vn.mcare.system.repository.model.tables.records.MedicalReportDetailRecord;
import vn.mcare.system.repository.model.tables.records.PrescriptionRecord;
import vn.mcare.system.service.intface.MedicalReportService;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalReportServiceImpl implements MedicalReportService {

  private final Gson gson;
  private final PrescriptionRepo prescriptionRepo;
  private final ExamineInfoRepo examineInfoRepo;
  private final MedicalReportDetailRepo medicalReportDetailRepo;
  private final HealthMaintenanceRepo healthMaintenanceRepo;
  private final MedicalHistoryRepo medicalHistoryRepo;
  private final MedicalReportRepo medicalReportRepo;
  private final DataListRepo dataListRepo;

  @Override
  public RestfulCommonResponse getPrescription(String examineId) {
    try {
      List<PrescriptionDto> output = prescriptionRepo.getByExamineId(examineId);
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse createPrescription(JsonObject payload) {
    try {
      CreatePrescriptionInput input = gson.fromJson(payload, CreatePrescriptionInput.class);

      PrescriptionRecord record = new PrescriptionRecord();
      record
              .setExamineId(input.getExamineId())
              .setMedicineId(input.getMedicineId())
              .setAmount(input.getAmount())
              .setCalUnit(input.getCalUnit())
              .setDescription(input.getDescription());

      prescriptionRepo.save(record);

      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse updatePrescription(JsonObject payload) {
    try {
      UpdatePrescriptionInput input = gson.fromJson(payload, UpdatePrescriptionInput.class);

      PrescriptionRecord record = prescriptionRepo.findOne(input.getId());
      if (record == null) {
        throw new RecordNotFound("Not found prescription");
      }

      record
              .setMedicineId(input.getMedicineId())
              .setAmount(input.getAmount())
              .setCalUnit(input.getCalUnit())
              .setDescription(input.getDescription());

      prescriptionRepo.update(record);

      return new RestfulSuccessResponse().setResponse(CodeResponse.OK);
    } catch (RecordNotFound e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse().setResponse(CodeResponse.BAD_REQUEST);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse deletePrescription(Integer id) {
    try {

      prescriptionRepo.delete(id);

      return new RestfulSuccessResponse().setMessages("Delete prescription success");
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getHealthMaintenance(String examineId) {
    try {
      List<HealthMaintenanceDto> output = healthMaintenanceRepo.getByExamineId(examineId);
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse createHealthMaintenance(JsonObject payload) {
    try {
      CreateHealthMaintenanceInput input = gson
              .fromJson(payload, CreateHealthMaintenanceInput.class);

      HealthMaintenanceRecord record = new HealthMaintenanceRecord();
      record
              .setExamineId(input.getExamineId())
              .setBloodPressureMin(input.getBloodPressureMin())
              .setBloodPressureMax(input.getBloodPressureMax())
              .setBloodSugar(input.getBloodSugar())
              .setCholesterol(input.getCholesterol())
              .setWeight(input.getWeight())
              .setHeight(input.getHeight())
              .setTemperature(input.getTemperature())
              .setCreatedAt(LocalDateTime.now())
              .setModifiedAt(LocalDateTime.now());

      healthMaintenanceRepo.save(record);

      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse updateHealthMaintenance(JsonObject payload) {
    try {
      UpdateHealthMaintenanceInput input = gson
              .fromJson(payload, UpdateHealthMaintenanceInput.class);

      HealthMaintenanceRecord record = healthMaintenanceRepo.findOne(input.getId());

      if (record == null) {
        throw new RecordNotFound("Not found health maintenance");

      }

      record
              .setExamineId(input.getExamineId())
              .setBloodPressureMin(input.getBloodPressureMin())
              .setBloodPressureMax(input.getBloodPressureMax())
              .setBloodSugar(input.getBloodSugar())
              .setCholesterol(input.getCholesterol())
              .setWeight(input.getWeight())
              .setHeight(input.getHeight())
              .setTemperature(input.getTemperature())
              .setModifiedAt(LocalDateTime.now());

      healthMaintenanceRepo.update(record);

      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (RecordNotFound e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse().setResponse(CodeResponse.BAD_REQUEST);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse deleteHealthMaintenance(Integer id) {
    try {

      healthMaintenanceRepo.delete(id);

      return new RestfulSuccessResponse().setMessages("Delete health maintenance success");
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getListExamine(String medicalReportId) {
    try {

      List<ExamineDto> output = examineInfoRepo.getByMedicalReport(medicalReportId);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse createExamine(JsonObject payload) {
    try {
      CreateExamineInput input = gson.fromJson(payload, CreateExamineInput.class);
      log.info(input.getMedicalReportId());
      String examineId = Generator.generate();

      ExamineInfoRecord record = new ExamineInfoRecord();
      record
              .setExamineId(examineId)
              .setStatus(EExamineStatus.EXAMINING.getCode())
              .setAttendDoctor(input.getAttendDoctor())
              .setDiagnose(input.getDiagnose())
              .setCheckIn(LocalDateTime.now())
              .setCreatedAt(LocalDateTime.now())
              .setModifiedAt(LocalDateTime.now());

      examineInfoRepo.save(record);

      MedicalReportDetailRecord medicalDetail = new MedicalReportDetailRecord();
      medicalDetail
              .setExamineId(examineId)
              .setMedicalReportId(input.getMedicalReportId())
              .setCreatedAt(LocalDateTime.now())
              .setModifiedAt(LocalDateTime.now());

      medicalReportDetailRepo.save(medicalDetail);

      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse updateExamine(JsonObject payload) {
    try {
      UpdateExamineInput input = gson.fromJson(payload, UpdateExamineInput.class);

      ExamineInfoRecord record = new ExamineInfoRecord();
      record
              .setAttendDoctor(input.getAttendDoctor())
              .setDiagnose(input.getDiagnose())
              .setModifiedAt(LocalDateTime.now());

      return new RestfulSuccessResponse().setResponse(CodeResponse.OK);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse changeStatusExamine(JsonObject payload) {
    try {
      ChangeExamineStatusInput input = gson.fromJson(payload, ChangeExamineStatusInput.class);

      ExamineInfoRecord record = examineInfoRepo.findOne(input.getExamineId());
      List<DataListDto> dataListByKey = dataListRepo.getDataListByKey(EDataList.EXAMINE_STATUS.getKey());

      Optional<DataListDto> dataListDto = dataListByKey
              .stream()
              .filter(item -> Integer.parseInt(item.getDataValue()) == input.getStatus())
              .findFirst();

      if (dataListDto == null) {
        throw new ExamineStatusInvalid("examine status invalid");
      }

      if (record == null) {
        throw new RecordNotFound("Not found examine");
      }

      if (record != null) {
        record.setStatus(input.getStatus());
        record.setAttendDoctor(input.getAttendDoctor());
        if (input.getStatus() == EExamineStatus.DIAGNOSE.getCode()) {
          record
                  .setDiagnose(input.getDiagnose());
        }

        if (input.getStatus() == EExamineStatus.FINISH.getCode()) {
          record
                  .setCheckOut(LocalDateTime.now());
        }

        record.setModifiedAt(LocalDateTime.now());
        examineInfoRepo.update(record);
      }

      return new RestfulSuccessResponse();
    } catch (RecordNotFound | ExamineStatusInvalid e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse().setResponse(CodeResponse.BAD_REQUEST);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getMedicalHistoryByCif(String cif) {
    try {
      List<ExamineDoneDto> output = examineInfoRepo.getExamineDoneByCif(cif);
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse deleteMedicalHistory(Integer id) {
    try {
      medicalHistoryRepo.delete(id);
      return new RestfulSuccessResponse().setMessages("Delete health history success");
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getExamineDetail(String cif) {
    try {
      MedicalReportDto dto = medicalReportRepo.getByCif(cif);
      if (dto == null) {
        throw new SQLException.RecordNotFound("Cif not have medical report");
      }
      ExamineDetailDto output = examineInfoRepo.getExaminingDetail(dto.getMedicalReportId());
      output.setMedicalReportId(dto.getMedicalReportId());
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }
}
