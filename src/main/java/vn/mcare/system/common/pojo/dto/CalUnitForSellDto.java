package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CalUnitForSellDto {
  private Integer calUnit;
  private String calUnitName;
  private Double sellPrice;
  private Double importPrice;
  private Integer amount;
}
