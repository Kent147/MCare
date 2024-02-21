package vn.mcare.system.common.pojo.api.output;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterExaminationOutput {

  private Integer id;
  private Integer status;
  private String fullName;
  private String email;
  private String phone;
  private String randomNumber;
  private Long fromDate;
  private Long toDate;

}
