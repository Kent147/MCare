package vn.mcare.system.common.pojo.api.input;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import vn.mcare.system.common.pojo.dto.ServiceDto;

@Data
@Accessors(chain = true)
public class GetAllServiceOutput {
  private Integer pageCount;
  private Integer page;
  private List<ServiceDto> list;
  private Integer limit;
}
