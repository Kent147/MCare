package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExamineDetailDto {
  private String medicalReportId;
  private String examineId;
  private String status;
  private Integer statusId;
  private String attendDoctor;
  private String attendDoctorName;
  private String diagnose;
  private Long checkIn;
  private Long checkOut;
  private Long createdAt;
  private Long modifiedAt;
}
