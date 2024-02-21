package vn.mcare.system.common.constant.config;

import lombok.Data;
import lombok.experimental.Accessors;
import vn.mcare.system.common.constant.start.DatabaseConfig;

@Data
@Accessors(chain = true)
public class ModuleConfig {

  private SSHTunnelConfig sshTunnel;
  private DatabaseConfig databaseConfig;
  private JooqGeneratorConfig jooqGenerator;
  private MailConfig mailConfig;
}
