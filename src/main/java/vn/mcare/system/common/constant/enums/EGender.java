package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EGender {
    MALE(1),
    FEMALE(0),
    UNKNOWN(-1);
    Integer code;
}
