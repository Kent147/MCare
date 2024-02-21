package vn.mcare.system.common.constant.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SSHTunnelConfig {

  private String sshUser;
  private String sshPassword;
  private Integer sshPort;
  private String sshHost;
  private String databaseHost;
  private Integer databasePort;
  private Integer localPort;
}
