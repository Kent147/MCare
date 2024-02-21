package vn.mcare.system.common.pojo.info;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RequestLog {
  private String method;
  private String requestUri;
  private String remoteAddress;
  private String remoteHost;
  private Integer remotePort;
  private String email;
  private String fullName;
  private String cif;
  private String token;
  private String contentType;
  private String userAgent;
}
