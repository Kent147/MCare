package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedicalReportDto {
  private String medicalReportId;
  private String cif;
  private String bloodGroup;
  private Long createdAt;
  private Long modifiedAt;
}
