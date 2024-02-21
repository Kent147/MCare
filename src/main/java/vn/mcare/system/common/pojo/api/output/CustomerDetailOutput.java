package vn.mcare.system.common.pojo.api.output;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CustomerDetailOutput {
  private String cif;
  private String fullName;
  private Integer gender;
  private Long bod;
  private String phone;
  private String email;
  private String cardId;
  private String nation;
  private String career;
  private String street;
  private Integer ward;
  private Integer district;
  private Integer province;
  private String description;
  private Long createdAt;
  private Long modifiedAt;
}
