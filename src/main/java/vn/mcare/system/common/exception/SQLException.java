package vn.mcare.system.common.exception;

public class SQLException {

  public static class SQLExcuteError extends BaseException {

    public SQLExcuteError(String msg) {
      super(msg);
    }

    public SQLExcuteError(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class SQLError extends BaseException {

    public SQLError(String msg) {
      super(msg);
    }

    public SQLError(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class RecordExisted extends BaseException {

    public RecordExisted(String msg) {
      super(msg);
    }

    public RecordExisted(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class RecordNotFound extends BaseException {

    public RecordNotFound(String msg) {
      super(msg);
    }

    public RecordNotFound(String msg, Throwable cause) {
      super(msg, cause);
    }
  }
}
