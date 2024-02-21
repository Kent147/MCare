package vn.mcare.system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.Print;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.service.intface.PrintService;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PrintController {

  private final PrintService printService;

  @GetMapping(value = Print.PRINT_PRESCRIPTION)
  public RestfulCommonResponse printPrescription(@PathVariable("id") String examineId) {
    return printService.printPrescription(examineId);
  }

}
