package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ECalUnit {
    PACKAGE(2, "Package"),
    PILL(1, "pill"),
    BOX(0, "bõ");
    Integer code;
    String description;
}
