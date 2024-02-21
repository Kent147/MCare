package vn.mcare.system.controller;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL;
import vn.mcare.system.common.constant.ServerURL.Service;
import vn.mcare.system.common.exception.CommonExceptions;
import vn.mcare.system.common.helper.validation.ValidationLayer;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.service.intface.ProductService;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
  private final ProductService productService;
  private final ValidationLayer validationLayer;

  @PostMapping(value = ServerURL.Medicine.GET)
  public RestfulCommonResponse displayMed(@RequestBody SearchInput search) {
    return productService.getMed(search);
  }

  @PostMapping(value = ServerURL.Medicine.CREATE)
  public RestfulCommonResponse createMed(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreateMedicine(payload);
      return productService.saveMed(payload);
    } catch (CommonExceptions.ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PutMapping(value = ServerURL.Medicine.UPDATE)
  public RestfulCommonResponse updateMed(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputUpdateMedicine(payload);
      return productService.updateMed(payload);
    } catch (CommonExceptions.ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @DeleteMapping(value = ServerURL.Medicine.DELETE)
  public RestfulCommonResponse deleteMed(@PathVariable("id") String medicineId) {
    return productService.deleteMed(medicineId);
  }

  @GetMapping(value = ServerURL.Medicine.FIND_BY_NAME)
  public RestfulCommonResponse findMedByName(@PathVariable("medicineName") String medicineName) {
    return productService.findMedByName(medicineName);
  }

  @GetMapping(value = ServerURL.Medicine.FIND_FOR_SELL)
  public RestfulCommonResponse findMedForSell(@PathVariable("medicineName") String medicineName) {
    return productService.findMedForSell(medicineName);
  }

  // SERVICE
  @PostMapping(value = ServerURL.Service.GET)
  public RestfulCommonResponse displayService(@RequestBody SearchInput search) {
    return productService.getService(search);
  }

  @PostMapping(value = ServerURL.Service.CREATE)
  public RestfulCommonResponse createService(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreateService(payload);
      return productService.saveService(payload);
    } catch (CommonExceptions.ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PutMapping(value = ServerURL.Service.UPDATE)
  public RestfulCommonResponse updateService(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputUpdateService(payload);
      return productService.updateService(payload);
    } catch (CommonExceptions.ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @DeleteMapping(value = ServerURL.Service.DELETE)
  public RestfulCommonResponse deleteService(@PathVariable("id") String serviceId) {
    return productService.deleteService(serviceId);
  }

  @GetMapping(value = Service.GET_BY_ID)
  public RestfulCommonResponse getServiceById(@PathVariable("id") String serviceId) {
    return productService.getServiceById(serviceId);
  }

  @GetMapping(value = Service.FIND_BY_NAME)
  public RestfulCommonResponse findServiceByName(@PathVariable("serviceName") String serviceName) {
    return productService.findServiceByName(serviceName);
  }
}

