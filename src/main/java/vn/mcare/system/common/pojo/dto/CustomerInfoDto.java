package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Accessors(chain = true)
public class CustomerInfoDto {

  private String cif;
  private String fullName;
  private Boolean gender;
  private String medicalReportId;
  private String bloodGroup;

}
