package vn.mcare.system.common.pojo.api.output;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import vn.mcare.system.common.pojo.dto.RevenueDto;

@Data
@Accessors(chain = true)
public class RevenueOutput {
  private Long fromDate;
  private Long toDate;
  private Double sumSubPrice;
  private Double sumVatPrice;
  private Double sumTotalPrice;
  private Double sumCostOfGoods;
  private Double sumRevenue;
  private List<RevenueDto> revenues;
}
