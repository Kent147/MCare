package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
public class CreatePrescriptionInput {
  private String examineId;
  private String medicineId;
  private Integer amount;
  private Integer calUnit;
  @CanNullOrEmpty
  private String description;
}
