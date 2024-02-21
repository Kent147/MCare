package vn.mcare.system.common.pojo.dto;

import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDto {

  private String cif;
  private String email;
  private String password;
  private String salt;
  private String fullName;
  private Boolean gender;
  private Date birthday;
  private String address;
  private String cardId;
  private String description;
  private Long createdAt;
  private Long modifiedAt;
}
