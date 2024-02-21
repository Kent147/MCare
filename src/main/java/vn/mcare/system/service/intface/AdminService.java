package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

@Component
public interface AdminService {

  RestfulCommonResponse getAdmins();

  RestfulCommonResponse createAdmin(JsonObject payload);

  RestfulCommonResponse updateAdmin(JsonObject payload);

  RestfulCommonResponse deleteAdmin(String adminId);
}
