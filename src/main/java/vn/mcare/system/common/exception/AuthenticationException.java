package vn.mcare.system.common.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationException {

  public static class PermissionsDeniedException extends BaseException {

    public PermissionsDeniedException(String msg) {
      super(msg);
    }

    public PermissionsDeniedException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class TokenExpiredException extends BaseException {

    public TokenExpiredException(String msg) {
      super(msg);
    }

    public TokenExpiredException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class ForbiddenException extends BaseException {

    public ForbiddenException(String msg) {
      super(msg);
    }

    public ForbiddenException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class JWTException extends BaseException {

    public JWTException(String msg) {
      super(msg);
    }

    public JWTException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class ValidationException extends BaseException {

    public ValidationException(String msg) {
      super(msg);
    }

    public ValidationException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class InvalidTokenException extends BaseException {

    public InvalidTokenException(String msg) {
      super(msg);
    }

    public InvalidTokenException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class InvalidUserException extends BaseException {

    public InvalidUserException(String msg) {
      super(msg);
    }

    public InvalidUserException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }

  public static class InvalidRequestException extends BaseException {

    public InvalidRequestException(String msg) {
      super(msg);
    }

    public InvalidRequestException(String msg, Throwable cause) {
      super(msg, cause);
    }
  }
}
