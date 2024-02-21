package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface ProductService {
  RestfulCommonResponse getMed(SearchInput search);

  RestfulCommonResponse saveMed(JsonObject payload);

  RestfulCommonResponse updateMed(JsonObject payload);

  RestfulCommonResponse deleteMed(String medicineId);

  RestfulCommonResponse findMedByName(String medicineName);

  RestfulCommonResponse findMedForSell(String medicineName);

  RestfulCommonResponse getService(SearchInput search);

  RestfulCommonResponse saveService(JsonObject payload);

  RestfulCommonResponse updateService(JsonObject payload);

  RestfulCommonResponse deleteService(String serviceId);

  RestfulCommonResponse getServiceById(String serviceId);

  RestfulCommonResponse findServiceByName(String serviceName);
}
