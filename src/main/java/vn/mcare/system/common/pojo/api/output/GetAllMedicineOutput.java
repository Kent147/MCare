package vn.mcare.system.common.pojo.api.output;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import vn.mcare.system.common.pojo.dto.MedicineDto;

@Data
@Accessors(chain = true)
public class GetAllMedicineOutput {
  private Integer pageCount;
  private Integer page;
  private List<MedicineDto> list;
  private Integer limit;
}
