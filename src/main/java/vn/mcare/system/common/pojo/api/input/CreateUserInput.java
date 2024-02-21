package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

import java.util.Date;

@Getter
public class CreateUserInput {

  private String email;
  private String password;
  private String fullName;
  private Integer gender;
  private Long bod;
  private Integer role;
  @CanNullOrEmpty
  private String address;
  private String cardId;
  @CanNullOrEmpty
  private String description;
}
