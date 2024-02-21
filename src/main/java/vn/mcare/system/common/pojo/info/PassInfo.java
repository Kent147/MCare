package vn.mcare.system.common.pojo.info;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class PassInfo {

  private String hash;
  private String salt;
}
