package vn.mcare.system.common.pojo.api.input;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChangePasswordInput {
  private String cif;
  private String oldPassword;
  private String newPassword;
}
