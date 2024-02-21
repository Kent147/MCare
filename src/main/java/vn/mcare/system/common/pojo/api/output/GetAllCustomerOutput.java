package vn.mcare.system.common.pojo.api.output;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetAllCustomerOutput {

  private Integer pageCount;
  private Integer page;
  private List<GetCustomerOutput> list;
  private Integer limit;

}
