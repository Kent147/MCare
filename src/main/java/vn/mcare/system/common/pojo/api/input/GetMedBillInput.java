package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
public class GetMedBillInput {
  private Integer page;
  private Integer limit;
  @CanNullOrEmpty
  private String search;
  @CanNullOrEmpty
  private Boolean isVat;
  @CanNullOrEmpty
  private Long fromDate;
  @CanNullOrEmpty
  private Long toDate;
}
