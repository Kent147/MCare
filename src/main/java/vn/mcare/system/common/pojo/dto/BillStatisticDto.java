package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BillStatisticDto {
  private Integer count;
  private Double sumTotalPrice;
}
