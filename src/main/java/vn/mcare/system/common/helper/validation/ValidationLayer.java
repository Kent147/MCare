package vn.mcare.system.common.helper.validation;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface ValidationLayer {

  void checkInputCreateUser(JsonElement payload);

  void checkInputUpdateUser(JsonElement payload);

  void checkInputUserLogin(JsonElement payload);

  void checkInputCreateMedicine(JsonElement payload);

  void checkInputUpdateMedicine(JsonElement payload);

  void checkInputRegisterExamination(JsonObject payload);

  void checkInputCreateCustomer(JsonElement payload);

  void checkInputUpdateCustomer(JsonElement payload);

  void checkInputUpdateHealthMaintenance(JsonObject payload);

  void checkInputCreateHealthMaintenance(JsonObject payload);

  void checkInputUpdatePrescription(JsonObject payload);

  void checkInputCreatePrescription(JsonObject payload);

  void checkInputCreateExamine(JsonObject payload);

  void checkInputCreateMedicineBill(JsonObject payload);

  void checkInputGetMedBill(JsonObject payload);

  void checkInputUpdateService(JsonObject payload);

  void checkInputCreateService(JsonObject payload);

  void checkInputCreateServiceBill(JsonObject payload);

  void checkInputStatistic(JsonObject payload);

  void checkInputChangeRegStatus(JsonObject payload);

  void checkInputChangeExamineStatus(JsonObject payload);

  void checkInputGetReceiveCustomerList(JsonObject payload);

  void checkInputReceiveCustomer(JsonObject payload);
}
