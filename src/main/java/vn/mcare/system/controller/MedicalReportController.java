package vn.mcare.system.controller;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.MedicalReport;
import vn.mcare.system.common.exception.CommonExceptions.ValidationException;
import vn.mcare.system.common.helper.validation.ValidationLayer;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.service.intface.MedicalReportService;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MedicalReportController {

  private final MedicalReportService medicalReportService;
  private final ValidationLayer validationLayer;

  @GetMapping(value = MedicalReport.GET_EXAMINE)
  public RestfulCommonResponse getListExamine(@PathVariable(value = "id") String medicalReportId) {
    return medicalReportService.getListExamine(medicalReportId);
  }

  @GetMapping(value = MedicalReport.GET_EXAMINE_DETAIL)
  public RestfulCommonResponse getExamineDetail(@PathVariable(value = "id") String medicalReportId) {
    return medicalReportService.getExamineDetail(medicalReportId);
  }

  @PostMapping(value = MedicalReport.CREATE_EXAMINE)
  public RestfulCommonResponse createExamine(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreateExamine(payload);
      return medicalReportService.createExamine(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @GetMapping(value = MedicalReport.GET_PRESCRIPTION)
  public RestfulCommonResponse getPrescription(@PathVariable(value = "id") String examineId) {
    return medicalReportService.getPrescription(examineId);
  }

  @PostMapping(value = MedicalReport.CREATE_PRESCRIPTION)
  public RestfulCommonResponse createPrescription(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreatePrescription(payload);
      return medicalReportService.createPrescription(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PutMapping(value = MedicalReport.CREATE_PRESCRIPTION)
  public RestfulCommonResponse updatePrescription(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputUpdatePrescription(payload);
      return medicalReportService.updatePrescription(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @DeleteMapping(value = MedicalReport.GET_PRESCRIPTION)
  public RestfulCommonResponse deletePrescription(@PathVariable(value = "id") Integer id) {
    return medicalReportService.deletePrescription(id);
  }

  @GetMapping(value = MedicalReport.GET_HEALTH_MAINTENANCE)
  public RestfulCommonResponse getHealthMaintenance(@PathVariable(value = "id") String examineId) {
    return medicalReportService.getHealthMaintenance(examineId);
  }

  @PostMapping(value = MedicalReport.CREATE_HEALTH_MAINTENANCE)
  public RestfulCommonResponse createHealthMaintenance(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreateHealthMaintenance(payload);
      return medicalReportService.createHealthMaintenance(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @DeleteMapping(value = MedicalReport.GET_HEALTH_MAINTENANCE)
  public RestfulCommonResponse deleteHealthMaintenance(@PathVariable(value = "id") Integer id) {
    return medicalReportService.deleteHealthMaintenance(id);
  }

  @GetMapping(value = MedicalReport.GET_MEDICAL_HISTORY_BY_CIF)
  public RestfulCommonResponse getMedicalHistoryByCif(@PathVariable(value = "cif") String cif) {
    return medicalReportService.getMedicalHistoryByCif(cif);
  }

  @DeleteMapping(value = MedicalReport.DELETE_HEALTH_HISTORY)
  public RestfulCommonResponse deleteHealthHistory(@PathVariable(value = "id") Integer id) {
    return medicalReportService.deleteMedicalHistory(id);
  }

  @PostMapping(value = MedicalReport.CHANGE_EXAMINE_STATUS)
  public RestfulCommonResponse changeExamineStatus(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputChangeExamineStatus(payload);
      return medicalReportService.changeStatusExamine(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

}
