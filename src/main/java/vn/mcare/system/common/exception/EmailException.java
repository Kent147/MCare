package vn.mcare.system.common.exception;

public class EmailException {

  public static class EmailSendFailException extends BaseException {

    public EmailSendFailException(String msg) {
      super(msg);
    }

    public EmailSendFailException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

}
