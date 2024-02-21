package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface BillService {

  RestfulCommonResponse getMedBill(JsonObject payload);

  RestfulCommonResponse createMedicineBill(JsonObject payload);

  RestfulCommonResponse genMedicineBill(String examineId);

  RestfulCommonResponse getMedBillDetail(String billId);

  RestfulCommonResponse getServiceBill(JsonObject payload);

  RestfulCommonResponse getServiceBillDetail(String billId);

  RestfulCommonResponse createServiceBill(JsonObject payload);
}
