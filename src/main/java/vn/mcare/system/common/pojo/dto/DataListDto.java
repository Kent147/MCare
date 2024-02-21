package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DataListDto {

  private Integer id;
  private String dataKey;
  private String dataValue;
  private String label;
  private String description;
}
