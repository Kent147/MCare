package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
public class CreateCustomerInput {

  private String fullName;
  private Integer gender;
  private String email;
  private Long bod;
  private String cardId;
  @CanNullOrEmpty
  private Integer nation;
  private String phone;
  @CanNullOrEmpty
  private Integer career;
  private String street;
  private Integer ward;
  private Integer district;
  private Integer province;
  @CanNullOrEmpty
  private String description;

}
