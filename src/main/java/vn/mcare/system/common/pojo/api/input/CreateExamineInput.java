package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
public class CreateExamineInput {
  private String medicalReportId;
  private String attendDoctor;
  @CanNullOrEmpty
  private String diagnose;
}
