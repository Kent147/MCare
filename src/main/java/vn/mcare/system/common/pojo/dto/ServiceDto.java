package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceDto {
  private String serviceId;
  private String serviceName;
  private Double price;
  private String description;
  private Long createdAt;
  private Long modifiedAt;
}
