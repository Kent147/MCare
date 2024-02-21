package vn.mcare.system.common.helper;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PassHelper {

  private static final Integer DEFAULT_ROUND = 5;

  public static String hashPw(String plainPassword, String salt) {
    return BCrypt.hashpw(plainPassword, salt);
  }

  public static boolean checkPw(String plainPw, String hashPw){
    return BCrypt.checkpw(plainPw, hashPw);
  }

  public static String genSalt() {
    return BCrypt.gensalt(DEFAULT_ROUND);
  }
}
