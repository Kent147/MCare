package vn.mcare.system.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.Customer;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.service.intface.RegisterExaminationService;

@RestController
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterExaminationController {

  private final RegisterExaminationService registerExaminationService;

  @GetMapping(Customer.REGISTER_EXAMINATION)
  public RestfulCommonResponse getAll() {
    return registerExaminationService.getAll();
  }
}
