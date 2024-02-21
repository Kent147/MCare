package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface StaffService {

  RestfulCommonResponse getReceiveCustomerList(JsonObject payload);

  RestfulCommonResponse receiveCustomer(JsonObject payload);

  RestfulCommonResponse getExamineToday();
}
