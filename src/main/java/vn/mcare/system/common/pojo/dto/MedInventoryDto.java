package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedInventoryDto {
  private String medicineId;
  private String inventoryId;
  private Integer amount;
  private Integer calUnit;
}
