package vn.mcare.system.common.pojo.api.output;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetAllUserOutput {
  private String cif;
  private String email;
  private String fullName;
  private Integer gender;
  private Long bod;
  private String address;
  private String cardId;
  private Integer role;
  private Long lastLogin;
  private Long createdAt;
  private Long modifiedAt;
}
