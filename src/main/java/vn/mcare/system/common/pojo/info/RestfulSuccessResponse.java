package vn.mcare.system.common.pojo.info;

import com.google.gson.JsonObject;
import vn.mcare.system.common.constant.req_resp.CodeResponse;

public class RestfulSuccessResponse extends RestfulCommonResponse {

  public RestfulSuccessResponse() {
    this.status = true;
    this.code = CodeResponse.OK.getCode();
    this.messages = CodeResponse.OK.getMessage();
    this.data = new JsonObject();
  }

}
