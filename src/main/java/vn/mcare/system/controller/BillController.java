package vn.mcare.system.controller;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.Bill;
import vn.mcare.system.common.exception.CommonExceptions.ValidationException;
import vn.mcare.system.common.helper.validation.ValidationLayer;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.service.intface.BillService;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BillController {

  private final BillService billService;
  private final ValidationLayer validationLayer;

  @PostMapping(value = Bill.GET_MEDICINE_BILL)
  public RestfulCommonResponse getMedBill(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputGetMedBill(payload);
      return billService.getMedBill(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @GetMapping(value = Bill.GEN_MEDICINE_BILL)
  public RestfulCommonResponse genMedicineBill(@PathVariable("id") String examineId) {
    return billService.genMedicineBill(examineId);
  }

  @GetMapping(value = Bill.GET_MEDICINE_BILL_DETAIL)
  public RestfulCommonResponse getMedBillDetail(@PathVariable("id") String billId) {
    return billService.getMedBillDetail(billId);
  }

  @PostMapping(value = Bill.CREATE_MEDICINE_BILL)
  public RestfulCommonResponse createMedicineBill(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreateMedicineBill(payload);
      return billService.createMedicineBill(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PostMapping(value = Bill.GET_SERVICE_BILL)
  public RestfulCommonResponse getServiceBill(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputGetMedBill(payload);
      return billService.getServiceBill(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @GetMapping(value = Bill.GET_SERVICE_BILL_DETAIL)
  public RestfulCommonResponse getServiceBillDetail(@PathVariable("id") String billId) {
    return billService.getServiceBillDetail(billId);
  }

  @PostMapping(value = Bill.CREATE_SERVICE_BILL)
  public RestfulCommonResponse createServiceBill(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreateServiceBill(payload);
      return billService.createServiceBill(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }
}
