package vn.mcare.system.common.pojo.api.input;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Data
@Accessors(chain = true)
public class UpdateCustomerInput {

  private String cif;
  private String fullName;
  private Integer gender;
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
