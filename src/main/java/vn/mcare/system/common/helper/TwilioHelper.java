package vn.mcare.system.common.helper;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TwilioHelper {
  private static PhoneNumber TWILIO_NUMBER = new PhoneNumber("+18317775026");

  public static void sendSMS(String phoneNumber, String body) {
    try {
      Message message = Message
              .creator(new PhoneNumber(phoneNumber), TWILIO_NUMBER, body)
              .create();
      log.info("Send sms success: " + message.getSid());
    } catch (Exception e) {
      log.error("Send sms failed: " + e);
    }
  }
}
