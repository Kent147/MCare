package vn.mcare.system.common.pojo.api.output;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatisticBillOutput {
  private Long fromDate;
  private Long toDate;
  private Integer countMedBill;
  private Integer countServiceBill;
  private Double sumMedBillPrice;
  private Double sumServiceBillPrice;
}
