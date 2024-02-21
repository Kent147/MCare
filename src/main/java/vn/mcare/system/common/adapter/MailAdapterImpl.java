package vn.mcare.system.common.adapter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.constant.config.ModuleConfig;
import vn.mcare.system.common.exception.EmailException;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Configuration
@Component
public class MailAdapterImpl implements MailAdapter {

  private final ModuleConfig moduleConfig;
  private Mailer mailer;

  @Bean
  public void createSessionSendMail() {
    try {
      mailer = MailerBuilder
          .withSMTPServer(moduleConfig.getMailConfig().getHost(),
              moduleConfig.getMailConfig().getPort(),
              moduleConfig.getMailConfig().getUsername(),
              moduleConfig.getMailConfig().getPassword())
          .trustingSSLHosts(moduleConfig.getMailConfig().getSsl())
          .withSessionTimeout(10 * 1000)
          .withProperty("mail.smtp.auth", moduleConfig.getMailConfig().getAuth())
          .withProperty("mail.smtp.starttls.enable", true)
          .async()
          .buildMailer();
      mailer.testConnection();
      log.info("Init MailAdapter success");
    } catch (Exception e) {
      log.error("Init MailAdapter error: ", e);
    }
  }

  @Override
  public void sendMail(String to, String cc, String subject, String body) {
    try {
      if (to == null) {
        log.error("Recipient is null");
        throw new EmailException.EmailSendFailException("Send mail fail");
      }
      Email email;
      if (cc != null) {
        email = EmailBuilder.startingBlank()
            .from("MCare Company", moduleConfig.getMailConfig().getUsername())
            .to(to)
            .cc(cc)
            .withSubject(subject)
            .withHTMLText(body)
            .buildEmail();
      } else {
        email = EmailBuilder.startingBlank()
            .from(moduleConfig.getMailConfig().getUsername())
            .to(to)
            .withSubject(subject)
            .withHTMLText(body)
            .buildEmail();
      }
      mailer.sendMail(email);
      log.error("Send mail success: " + to);
    } catch (Exception e) {
      log.error("Error send mail : {}", e);
      throw new EmailException.EmailSendFailException("Send mail fail");
    }

  }

  @Override
  public void sendMail(String from, String fromName, List<String> tos, List<String> ccs,
      String subject, String body) {
    try {
      Email email = EmailBuilder.startingBlank()
          .from(moduleConfig.getMailConfig().getUsername())
          .toMultiple(tos)
          .cc("cc", ccs)
          .withSubject(subject)
          .withHTMLText(body)
          .buildEmail();
      mailer.sendMail(email);
    } catch (Exception e) {
      log.error("Error send mail : {}", e);
    }
  }
}
