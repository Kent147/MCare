package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceBillDetailDto {
  private String serviceId;
  private String serviceName;
  private Integer quantity;
  private Double price;
  private String description;
}
