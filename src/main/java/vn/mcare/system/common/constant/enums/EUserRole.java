package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EUserRole {

  ADMIN(999, "administrator"),
  DOCTOR(1, "doctor"),
  USERS(0, "normal users");
  Integer code;
  String description;
}
