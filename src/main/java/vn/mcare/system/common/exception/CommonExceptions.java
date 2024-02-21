package vn.mcare.system.common.exception;

public class CommonExceptions {

  public static class ValidationException extends BaseException {

    public ValidationException(String msg) {
      super(msg);
    }

    public ValidationException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class ValidationError extends BaseException {

    public ValidationError(String msg) {
      super(msg);
    }

    public ValidationError(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class UpdateExceptionDenied extends BaseException {

    public UpdateExceptionDenied(String msg) {
      super(msg);
    }

    public UpdateExceptionDenied(String msg, Throwable cause) {
      super(msg, cause);
    }
  }
}
