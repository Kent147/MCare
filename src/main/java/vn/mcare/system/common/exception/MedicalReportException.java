package vn.mcare.system.common.exception;

public class MedicalReportException {
  public static class ExamineStatusInvalid extends BaseException {

    public ExamineStatusInvalid(String msg) {
      super(msg);
    }

    public ExamineStatusInvalid(String msg, Throwable cause) {
      super(msg, cause);
    }
  }
}
