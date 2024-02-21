package vn.mcare.system.common.pojo.api.output;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserLoginOutput {
  private String email;
  private String fullName;
  private String cif;
  private String token;
  private Long expiredDate;
}
