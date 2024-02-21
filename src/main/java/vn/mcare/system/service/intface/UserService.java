package vn.mcare.system.service.intface;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.pojo.api.input.ChangePasswordInput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

@Component
public interface UserService {

  RestfulCommonResponse fetchAll();

  RestfulCommonResponse save(JsonObject payload);

  RestfulCommonResponse update(JsonObject payload);

  RestfulCommonResponse delete(String userId);

  RestfulCommonResponse checkValidEmail(String email);

  RestfulCommonResponse checkValidCardId(String cardId);

}
