package vn.mcare.system.common.helper.validation;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
public class ChangeExamineStatusInput {
  private String attendDoctor;
  private String examineId;
  private Integer status;
  @CanNullOrEmpty
  private String diagnose;
}
