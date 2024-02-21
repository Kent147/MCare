package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UpdateMedicineInput {
  private String medicineId;
  private String medicineName;
  private String source;
}
