package vn.mcare.system.service.intface;

import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface PrintService {
  RestfulCommonResponse printPrescription(String examineId);
}
