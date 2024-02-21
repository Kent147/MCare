package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
public class ReceiveCustomerInput {
  private String cif;
  private Integer status;
  private Long checkIn;
  @CanNullOrEmpty
  private String description;
}
