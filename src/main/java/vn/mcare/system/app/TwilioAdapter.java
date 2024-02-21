package vn.mcare.system.app;

import com.twilio.Twilio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TwilioAdapter {

  public static final String ACCOUNT_SID = "AC4e677fd58630392a8e7edefc8a854c1e";
  public static final String AUTH_TOKEN = "ec2c50ea7c7a876bc47de4293feda994";

  @Bean
  public void initSms() {
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    log.info("Init Twilio success");
  }
}
