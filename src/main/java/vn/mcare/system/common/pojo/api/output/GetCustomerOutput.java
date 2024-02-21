package vn.mcare.system.common.pojo.api.output;

import java.sql.Timestamp;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GetCustomerOutput {

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
  private String ward;
  private Integer wardId;
  private String district;
  private Integer districtId;
  private String province;
  private Integer provinceId;
  private String description;
  private Timestamp createdAt;
  private Timestamp modifiedAt;

}
