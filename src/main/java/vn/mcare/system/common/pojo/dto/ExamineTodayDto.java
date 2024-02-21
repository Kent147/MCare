package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExamineTodayDto {
  private String examineId;
  private String fullName;
  private String diagnose;
  private Long bod;
  private Integer gender;
}
