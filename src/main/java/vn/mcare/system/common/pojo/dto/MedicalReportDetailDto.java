package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedicalReportDetailDto {

  private Long id;
  private String medicalReportId;
  private String examineId;
  private Long status;

}
