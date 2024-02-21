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
import vn.mcare.system.common.constant.ServerURL.Customer;
import vn.mcare.system.common.exception.CommonExceptions.ValidationException;
import vn.mcare.system.common.helper.validation.ValidationLayer;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.service.intface.CustomerService;
import vn.mcare.system.service.intface.RegisterExaminationService;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController {

  private final ValidationLayer validationLayer;
  private final CustomerService customerService;
  private final RegisterExaminationService registerExaminationService;

  @PostMapping(value = Customer.REGISTER_EXAMINATION)
  public RestfulCommonResponse registerExamination(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputRegisterExamination(payload);
      return customerService.registerExamination(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PostMapping(value = Customer.GET)
  public RestfulCommonResponse fetchAll(@RequestBody SearchInput search) {
    return customerService.getAllCustomer(search);
  }

  @PostMapping(value = Customer.CREATE)
  public RestfulCommonResponse create(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreateCustomer(payload);
      return customerService.save(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PutMapping(value = Customer.UPDATE)
  public RestfulCommonResponse update(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputUpdateCustomer(payload);
      return customerService.update(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @DeleteMapping(value = Customer.DELETE)
  public RestfulCommonResponse delete(@PathVariable(value = "cif") String cif) {
    return customerService.delete(cif);
  }

  @GetMapping(value = Customer.SEARCH_BY_NAME)
  public RestfulCommonResponse search(@PathVariable(value = "name") String customerName) {
    return customerService.searchCustomerByFullName(customerName);
  }

  @GetMapping(value = Customer.GET_BY_CIF)
  public RestfulCommonResponse getByCif(@PathVariable(value = "cif") String cif) {
    return customerService.getByCif(cif);
  }

  @PostMapping(value = Customer.REGISTER_CHANGE_STATUS)
  public RestfulCommonResponse getByCif(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputChangeRegStatus(payload);
      return registerExaminationService.changeStatus(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @GetMapping(value = Customer.RECEIVE_REGISTER_EXAMINATION)
  public RestfulCommonResponse receiveReg(@PathVariable(value = "id") Integer id) {
    return registerExaminationService.receiveReg(id);
  }


}
