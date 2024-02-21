package vn.mcare.system.common.adapter;

import java.util.List;
import javax.activation.DataSource;

public interface MailAdapter {

  void sendMail(String to, String cc, String subject, String body);

  void sendMail(String from, String fromName, List<String> tos, List<String> ccs, String subject,
      String body);
}
