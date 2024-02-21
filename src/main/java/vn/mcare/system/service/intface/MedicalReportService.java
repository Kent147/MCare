package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface MedicalReportService {

  RestfulCommonResponse getListExamine(String medicalReportId);

  RestfulCommonResponse createExamine(JsonObject payload);

  RestfulCommonResponse updateExamine(JsonObject payload);

  RestfulCommonResponse changeStatusExamine(JsonObject payload);

  RestfulCommonResponse getPrescription(String examineId);

  RestfulCommonResponse createPrescription(JsonObject payload);

  RestfulCommonResponse updatePrescription(JsonObject payload);

  RestfulCommonResponse deletePrescription(Integer id);

  RestfulCommonResponse getHealthMaintenance(String examineId);

  RestfulCommonResponse createHealthMaintenance(JsonObject payload);

  RestfulCommonResponse updateHealthMaintenance(JsonObject payload);

  RestfulCommonResponse deleteHealthMaintenance(Integer id);

  RestfulCommonResponse getMedicalHistoryByCif(String cif);

  RestfulCommonResponse deleteMedicalHistory(Integer id);

  RestfulCommonResponse getExamineDetail(String medicalReportId);
}
