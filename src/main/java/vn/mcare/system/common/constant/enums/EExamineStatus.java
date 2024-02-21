package vn.mcare.system.common.constant.enums;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EExamineStatus {
  FINISH(3, "finish examine"),
  DIAGNOSE(2, "diagnose"),
  EXAMINING(1, "examining"),
  PENDING(0, "waiting to examine");
  Integer code;
  String description;

  public static Boolean isValid(Integer code) {
    EExamineStatus result = Stream.of(EExamineStatus.values())
            .filter(status -> status.getCode().equals(code))
            .findAny()
            .orElse(null);
    if (result == null) {
      return false;
    }
    return true;
  }
}
