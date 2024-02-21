package vn.mcare.system.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
  UNKNOWN_ERROR("Unknown error: {}"),
  SQL_ERROR("SQL error: {}");
  String message;
}
