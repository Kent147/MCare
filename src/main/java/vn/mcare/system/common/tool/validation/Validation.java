package vn.mcare.system.common.tool.validation;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.annotation.CanEmpty;
import vn.mcare.system.common.annotation.CanNullOrEmpty;
import vn.mcare.system.common.annotation.NotNull;
import vn.mcare.system.common.annotation.NotNullOrEmpty;
import vn.mcare.system.common.exception.CommonExceptions;
import vn.mcare.system.common.exception.CommonExceptions.ValidationError;

@Slf4j
@Component
public class Validation implements IValidation {


  @Override
  public void checkValidPhoneNumber(String phone) {
    if (phone == null) {
      throw new CommonExceptions.ValidationError("Phone invalid");
    }
    if (!phone.trim().matches("\\d{9,13}")) {
      throw new CommonExceptions.ValidationError("Phone invalid");
    }

  }

  @Override
  public void checkValidEmailAddress(String email) throws CommonExceptions.ValidationError {
    String patternEmail = "([a-z0-9A-Z!#$%&'*-+/=?^_`{|}~.]{3,})@([a-z0-9A-Z:.\\-]{5,})";
    if (!email.matches(patternEmail)) {
      throw new CommonExceptions.ValidationError("Email invalid");
    }
  }

  @Override
  public boolean isValidEmailAddress(String email) {
    String patternEmail = "([a-z0-9A-Z!#$%&'*-+/=?^_`{|}~.]{3,})@([a-z0-9A-Z:.\\-]{5,})";
    if (!email.matches(patternEmail)) {
      return false;
    }
    return true;
  }

  @Override
  public void checkNotNullAll(Object object) {
    if (object == null) {
      throw new CommonExceptions.ValidationError("Object is null");
    }
    boolean result = false;
    for (Field f : object.getClass().getDeclaredFields()) {
      try {
        f.setAccessible(true);
        if (f.get(object) != null) {
          result = true;
          break;
        }
      } catch (IllegalAccessException e) {
        log.error("Error with accessible object cause by {} ", e.getMessage());
      }
    }
    if (!result) {
      throw new CommonExceptions.ValidationError("Object don't have property");
    }
  }

  @Override
  public void checkSpecialCharacter(Object object) {
    if (object == null) {
      throw new CommonExceptions.UpdateExceptionDenied("Object is null");
    }
    for (Field f : object.getClass().getDeclaredFields()) {
      try {
        f.setAccessible(true);
        if ((f.get(object) != null)) {
          if (f.getType().equals(String.class)) {
            log.debug("This is String: FIELD:{} VALUE:{}", f.getName(), f.get(object).toString());
            String data = (String) f.get(object);
            checkSpecialCharacter(data);
          } else {
            log.debug("{} :this is not string", f.getName());
          }
        }
      } catch (IllegalAccessException e) {
        log.error("Error with accessible object cause by {} ", e.getMessage());
      }
    }
  }

  @Override
  public void checkValidPassword(String password) {
    String passwordPattern = ".{32,1024}";
    if (!password.matches(passwordPattern)) {
      throw new CommonExceptions.ValidationError("password must be from 32 to 1024 characters!");
    }
  }


  @Override
  public void checkValidClearTextPassword(String password) {
    //String passwordPattern = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
    String passwordPattern = "([a-zA-Z0-9!@#$%^&*_]{8,40})";

    if (!password.matches(passwordPattern)) {
      throw new CommonExceptions.ValidationError(
          "password must be from 8 to 40 characters, without spacing!");
    }
  }

  @Override
  public Boolean checkTimeFromTo(Date date, Date fromDate, Date toDate) {
    return date.after(fromDate) && date.before(toDate);
  }

  /**
   * Check null and empty all field with annotation NotNullOrEmpty Check null all field with
   * annotation NotNull
   */
  @Override
  public IValidation checkNotNullOrEmptyWithAnnotation(Object object) {
    Objects.requireNonNull(object, "Object is null");
    for (Field f : object.getClass().getDeclaredFields()) {
      try {
        f.setAccessible(true);
        if (f.isAnnotationPresent(NotNull.class) || f.isAnnotationPresent(NotNullOrEmpty.class)) {
          if (null == f.get(object)) {
            throw new ValidationError("Properties " + f.getName() + " is null");
          }
        }
        if (f.isAnnotationPresent(NotNullOrEmpty.class) && f.get(object) instanceof String) {
          if (States.isNullOrEmpty((String) f.get(object))) {
            throw new CommonExceptions.ValidationError(
                "Properties " + f.getName() + " is empty");
          }
        }
        if (f.isAnnotationPresent(NotNullOrEmpty.class) && f.get(object) instanceof Collection) {
          if (States.isNullOrEmpty((Collection) f.get(object))) {
            throw new CommonExceptions.ValidationError(
                "Properties " + f.getName() + " is empty");
          }
        }

      } catch (IllegalAccessException e) {
        log.error("Error with accessible object cause by {} ", e.getMessage());
      }
    }

    return this;
  }

  /**
   * Check not null and empty all field without annotation CanNullOrEmpty Check not null all field
   * without annotation CanEmpty
   */
  @Override
  public IValidation checkCanNullWithAnnotation(Object object) {
    if (object == null) {
      throw new CommonExceptions.ValidationError("Object is null");
    }
    for (Field f : object.getClass().getDeclaredFields()) {
      try {
        f.setAccessible(true);
        if (f.isAnnotationPresent(CanNullOrEmpty.class)) {
          continue;
        }
        if (f.isAnnotationPresent(CanEmpty.class)) {
          if (States.isNull(f.get(object))) {
            throw new CommonExceptions.ValidationError("Properties " + f.getName() + " is null");
          }
          continue;
        }
        if (States.isNull(f.get(object)) || States.isNullOrEmpty(String.valueOf(f.get(object)))) {
          throw new CommonExceptions.ValidationError(
              "Properties " + f.getName() + " is null or empty");
        }
      } catch (IllegalAccessException e) {
        log.error("Error with accessible object cause by {} ", e.getMessage());
      }
    }
    return this;
  }


}
