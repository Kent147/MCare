package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PrescriptionDto {
  private Integer id;
  private String medicineId;
  private String medicineName;
  private Integer amount;
  private String calUnit;
  private Integer calUnitId;
  private Long createdAt;
}
