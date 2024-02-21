package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RevenueDto {
  private Long date;
  private Double subPrice;
  private Double vatPrice;
  private Double totalPrice;
  private Double costOfGoods;
  private Double revenue;
}
