package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceInventoryDto {
  private String serviceId;
  private Double sellPrice;
  private Integer amount;
}
