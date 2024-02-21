package vn.mcare.system.common.pojo.api.output;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetMedBillOutput {
  private Integer pageCount;
  private Integer page;
  private List<MedBillOutput> list;
  private Integer limit;
}
