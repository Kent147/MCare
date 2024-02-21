package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
public class CreateServiceInput {
  private String serviceName;
  private Double price;
  @CanNullOrEmpty
  private String description;
}
