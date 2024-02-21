package vn.mcare.system.common.pojo.dto;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedBillDto {
  private String billId;
  private List<MedBillDetailDto> medicines;
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
