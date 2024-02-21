package vn.mcare.system.common.constant.start;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DatabaseConfig {

  private String classDriver;
  private String database;
  private String url;
  private String username;
  private String password;
}
