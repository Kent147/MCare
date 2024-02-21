package vn.mcare.system.common.pojo.api.input;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Data
@Accessors(chain = true)
public class SearchInput {
  private Integer page;
  private Integer limit;
  @CanNullOrEmpty
  private String search;
  @CanNullOrEmpty
  private String sort;
  private Boolean asc;
}
