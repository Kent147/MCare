package vn.mcare.system.common.pojo.api.output;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import vn.mcare.system.common.pojo.dto.ExamineDto;

@Data
@Accessors(chain = true)
public class ReceiveCustomerListOutput {
  private Integer pageCount;
  private Integer page;
  private List<ExamineDto> list;
  private Integer limit;
}
