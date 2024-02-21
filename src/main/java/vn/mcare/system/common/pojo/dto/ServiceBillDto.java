package vn.mcare.system.common.pojo.dto;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ServiceBillDto {
  private String billId;
  private List<ServiceBillDetailDto> services;
  private Double subPrice;
  private Boolean isVat;
  private Double vatPrice;
  private Double totalPrice;
  private String customerId;
  private String customerName;
  private String createBy;
  private Long billDate;
  private String description;
}
