package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ETokenType {
  ACCESS_TOKEN(1, "token use to access service");
  Integer code;
  String description;
}
