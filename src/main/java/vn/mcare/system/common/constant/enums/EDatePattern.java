package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EDatePattern {
  hhmm("hh:mm"),
  ddMMYYY("dd/MM/YYYY");
  String val;
}
