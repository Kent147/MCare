package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface AuthorService {

  RestfulCommonResponse userLogin(JsonObject payload);

  RestfulCommonResponse userLogout(String cif);

  RestfulCommonResponse changePassword(JsonObject payload);

}
