package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EDataList {

  MEDICAL_EXAMINE_STATUS("MEDICAL_EXAMINE_STATUS", "status of examine in medical report"),
  REG_STATUS("REGSTATUS", "status of register examination"),
  EXAMINE_STATUS("EXAMINE_STATUS", "status of examine"),
  COUNTRY("COUNTRY", "countries"),
  NATION("NATION", "national"),
  CAL_UNIT("CAL_UNIT", "cal unit of medicine");

  String key;
  String description;
}
