package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface RegisterExaminationService {

  RestfulCommonResponse getAll();

  RestfulCommonResponse changeStatus(JsonObject payload);

  RestfulCommonResponse receiveReg(Integer id);
}
