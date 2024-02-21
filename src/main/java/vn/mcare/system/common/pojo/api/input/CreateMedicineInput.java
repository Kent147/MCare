package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
public class CreateMedicineInput {
//    private String medicineId;
    private String medicineName;
    @CanNullOrEmpty
    private String source;
}
