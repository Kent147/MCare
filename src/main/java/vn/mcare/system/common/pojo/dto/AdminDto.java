package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminDto {

  private String adminId;
  private String email;
  private String password;
  private String salt;
  private String fullName;
  private String description;
  private Long lastLogin;
  private Long createdAt;
  private Long modifiedAt;
}
