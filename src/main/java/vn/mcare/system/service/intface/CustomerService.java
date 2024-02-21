package vn.mcare.system.service.intface;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface CustomerService {

  RestfulCommonResponse registerExamination(JsonObject payload);

  RestfulCommonResponse getAllCustomer(SearchInput input);

  RestfulCommonResponse getByCif(String cif);

  RestfulCommonResponse save(JsonObject payload);

  RestfulCommonResponse update(JsonElement payload);

  RestfulCommonResponse delete(String cif);

  RestfulCommonResponse searchCustomerByFullName(String customeName);

}
