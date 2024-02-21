package vn.mcare.system.common.pojo.info;

import com.google.gson.JsonElement;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vn.mcare.system.common.constant.req_resp.CodeResponse;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class RestfulCommonResponse implements Serializable {

  protected Boolean status;
  protected Integer code;
  protected JsonElement data;
  protected String messages;

  public RestfulCommonResponse putData(String key, JsonElement value) {
    this.data.getAsJsonObject().add(key, value);
    return this;
  }

  public RestfulCommonResponse setResponse(CodeResponse code) {
    this.code = code.getCode();
    this.messages = code.getMessage();
    return this;
  }

}
