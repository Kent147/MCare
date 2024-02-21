package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;

@Getter
public class UpdatePrescriptionInput {
  private Integer id;
  private String medicineId;
  private Integer amount;
  private Integer calUnit;
  private String description;
}
