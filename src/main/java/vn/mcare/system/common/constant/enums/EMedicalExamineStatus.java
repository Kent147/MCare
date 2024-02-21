package vn.mcare.system.common.constant.enums;

import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EMedicalExamineStatus {
  FINISH(2, "finish examine"),
  EXAMINING(1, "examining"),
  PENDING(0, "waiting to examine");
  Integer code;
  String description;

  public static Boolean isValid(Integer code) {
    EMedicalExamineStatus result = Stream.of(EMedicalExamineStatus.values())
            .filter(status -> status.getCode().equals(code))
            .findAny()
            .orElse(null);
    if (result == null) {
      return false;
    }
    return true;
  }
}
