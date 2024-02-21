package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedBillDetailDto {
  private String medicineId;
  private String inventoryId;
  private String lotId;
  private String medicineName;
  private Integer amount;
  private String calUnit;
}
