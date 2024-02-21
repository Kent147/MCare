package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;

@Getter
public class UpdateExamineInput {
  private String examineId;
  private String attendDoctor;
  private String diagnose;
}
