package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ERequestMethod {

  OPTIONS("OPTIONS", "request method OPTIONS"),
  DELETE("DELETE", "request method DELETE"),
  PUT("PUT", "request method PUT"),
  POST("POST", "request method POST"),
  GET("GET", "request method GET");

  String method;
  String description;
}
