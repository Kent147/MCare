package vn.mcare.system.common.pojo.api.input;

import lombok.Data;
import lombok.ToString;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Data
@ToString
public class UpdateUserInput {

  private String cif;
  private String email;
  private String fullName;
  private Integer gender;
  private Long birthday;
  @CanNullOrEmpty
  private Integer role;
  @CanNullOrEmpty
  private String address;
  private String cardId;
  @CanNullOrEmpty
  private String description;
}
