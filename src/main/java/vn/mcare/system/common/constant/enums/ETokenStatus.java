package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETokenStatus {
  NORMAL(1, "normal token"),
  BANNED(0, "banned token");
  Integer code;
  String description;
}
