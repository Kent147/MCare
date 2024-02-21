package vn.mcare.system.controller;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.Staff;
import vn.mcare.system.common.exception.CommonExceptions.ValidationException;
import vn.mcare.system.common.helper.validation.ValidationLayer;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.service.intface.CustomerService;
import vn.mcare.system.service.intface.RegisterExaminationService;
import vn.mcare.system.service.intface.StaffService;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StaffController {
  private final ValidationLayer validationLayer;
  private final CustomerService customerService;
  private final StaffService staffService;
  private final RegisterExaminationService registerExaminationService;

  @PostMapping(value = Staff.GET_RECEIVE_CUSTOMER_LIST)
  public RestfulCommonResponse getReceiveCustomerList(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputGetReceiveCustomerList(payload);
      return staffService.getReceiveCustomerList(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PostMapping(value = Staff.CREATE_RECEIVE_CUSTOMER)
  public RestfulCommonResponse receiveCustomer(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputReceiveCustomer(payload);
      return staffService.receiveCustomer(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @GetMapping(value = Staff.GET_EXAMINE_TODAY)
  public RestfulCommonResponse getExamineToday() {
    return staffService.getExamineToday();
  }
}
