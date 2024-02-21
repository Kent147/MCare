package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WardDto {

  private Integer id;
  private String name;
  private String prefix;
}
