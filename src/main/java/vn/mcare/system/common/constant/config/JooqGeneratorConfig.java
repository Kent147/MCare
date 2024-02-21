package vn.mcare.system.common.constant.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class JooqGeneratorConfig {

  private String jdbcDriver;
  private String jdbcUrl;
  private String user;
  private String password;
  private String jooqDatabaseName;
  private String includes;
  private String excludes;
  private String inputSchema;
  private String packageName;
  private String directory;
}
