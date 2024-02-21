package vn.mcare.system.common.pojo.api.input;

import java.util.List;
import lombok.Getter;
import vn.mcare.system.common.pojo.dto.MedInventoryDto;

@Getter
public class CreateMedicineBillInput {
  private Long billDate;
  private String customerId;
  private String createdBy;
  private Double totalPrice;
  private Double subPrice;
  private Double vatPrice;
  private Boolean isVat;
  private Integer vatRate;
  private List<MedInventoryDto> medicines;
}
