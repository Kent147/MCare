package vn.mcare.system.common.pojo.api.input;

import java.util.List;
import lombok.Getter;
import vn.mcare.system.common.pojo.dto.ServiceInventoryDto;

@Getter
public class CreateServiceBillInput {
  private Long billDate;
  private String customerId;
  private String createdBy;
  private Double subPrice;
  private Double vatPrice;
  private Boolean isVat;
  private Integer vatRate;
  private Double totalPrice;
  private List<ServiceInventoryDto> services;
}
