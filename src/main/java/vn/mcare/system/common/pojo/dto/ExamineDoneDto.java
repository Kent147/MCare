package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExamineDoneDto {
  private String attendDoctor;
  private String diagnose;
  private String status;
  private Long checkOut;
}
