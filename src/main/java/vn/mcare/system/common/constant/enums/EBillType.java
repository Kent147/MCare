package vn.mcare.system.common.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EBillType {

    MEDICINE_EXPORT(2, "medicine export bill"),
    SERVICE_EXPORT(1, "medicine import bill"),
    SERVICE(0, "service bill");
    Integer code;
    String description;
}
