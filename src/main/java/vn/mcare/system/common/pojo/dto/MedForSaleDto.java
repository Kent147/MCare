package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedForSaleDto {
  private String inventoryId;
  private String medicineId;
  private String medicineName;
  private String calUnitName;
  private Integer calUnit;
  private Double importPrice;
  private Double sellPrice;
  private Integer amount;
}
