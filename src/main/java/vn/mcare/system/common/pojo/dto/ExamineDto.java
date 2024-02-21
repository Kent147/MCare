package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExamineDto {
  private String examineId;
  private String status;
  private String fullName;
  private String attendDoctor;
  private String diagnose;
  private String createBy;
  private Long checkIn;
  private Long checkOut;
}
