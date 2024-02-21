package vn.mcare.system.service.intface;

import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface CommonService {

  RestfulCommonResponse getDataListByKey(String dataKey);

  RestfulCommonResponse getProvinces();

  RestfulCommonResponse getDistrict(Integer provinceId);

  RestfulCommonResponse getWard(Integer districtId);

  RestfulCommonResponse getDoctor();
}
