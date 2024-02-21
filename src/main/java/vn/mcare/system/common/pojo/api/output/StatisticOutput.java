package vn.mcare.system.common.pojo.api.output;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class StatisticOutput {

  private Integer totalBill;
  private Double totalIncome;
  private Integer countRegister;

}
