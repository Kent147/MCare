package vn.mcare.system.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.Common;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.service.intface.CommonService;

@Slf4j
@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommonController {

  private final CommonService commonService;

  @GetMapping(value = Common.GET_DATA_LIST)
  public RestfulCommonResponse registerExamination(@PathVariable("key") String dataKey) {
    return commonService.getDataListByKey(dataKey);
  }

  @GetMapping(value = Common.GET_PROVINCE)
  public RestfulCommonResponse getProvince() {
    return commonService.getProvinces();
  }

  @GetMapping(value = Common.GET_DISTRICT)
  public RestfulCommonResponse getDistrict(@PathVariable("id") Integer provinceId) {
    return commonService.getDistrict(provinceId);
  }

  @GetMapping(value = Common.GET_WARD)
  public RestfulCommonResponse getWard(@PathVariable("id") Integer districtId) {
    return commonService.getWard(districtId);
  }

  @GetMapping(value = Common.GET_DOCTOR)
  public RestfulCommonResponse getDoctor() {
    return commonService.getDoctor();
  }
}
