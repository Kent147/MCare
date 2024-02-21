package vn.mcare.system.common.constant.req_resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeResponse {
  CREATED(201, "object created"),
  INTERNAL_SERVER(500, "Internal Server Error"),
  UNAUTHORIZED(401, "Unauthorized"),
  BAD_REQUEST(400, "Bad Request"),
  OK(200, "OK");

  Integer code;
  String message;
}
