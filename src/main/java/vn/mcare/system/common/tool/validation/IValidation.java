package vn.mcare.system.common.tool.validation;

import java.util.Date;

public interface IValidation {

  void checkValidPhoneNumber(String phone);

  void checkValidEmailAddress(String email);

  boolean isValidEmailAddress(String email);

  void checkNotNullAll(Object object);

  void checkSpecialCharacter(Object object);

  void checkValidPassword(String password);

  void checkValidClearTextPassword(String password);

  Boolean checkTimeFromTo(Date date, Date fromDate, Date toDate);

  IValidation checkNotNullOrEmptyWithAnnotation(Object object);

  IValidation checkCanNullWithAnnotation(Object object);

}
