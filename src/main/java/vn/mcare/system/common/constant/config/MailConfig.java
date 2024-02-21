package vn.mcare.system.common.constant.config;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MailConfig {

  private String host;
  private Integer port;
  private String username;
  private String password;
  private Boolean auth;
  private String ssl;
  private String link;
}
