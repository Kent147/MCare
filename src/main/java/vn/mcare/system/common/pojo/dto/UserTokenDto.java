package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserTokenDto {
  private Integer id;
  private String cif;
  private String token;
  private Integer tokenType;
  private Integer status;
  private Long expireDate;
  private Long createdAt;
  private Long modifiedAt;
}
