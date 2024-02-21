package vn.mcare.system.common.helper;

import lombok.extern.slf4j.Slf4j;
import vn.mcare.system.common.constant.enums.EDatePattern;
import vn.mcare.system.common.pojo.api.output.RegisterExaminationOutput;

@Slf4j
public class ContentFormatHelper {

  public static String registerExamination(RegisterExaminationOutput customer) {
    String fromTime = DateHelper.convertToString(EDatePattern.hhmm, customer.getFromDate());
    String toTime = DateHelper.convertToString(EDatePattern.hhmm, customer.getToDate());
    String date = DateHelper.convertToString(EDatePattern.ddMMYYY, customer.getFromDate());

    String result = String.format(""
            + "\nHọ tên: %s\n"
            + "Email: %s\n"
            + "Số thứ tự: %s\n"
            + "Thời gian dự kiến khám bệnh: \n"
            + "%s - %s %s",
        customer.getFullName(), customer.getEmail(), customer.getRandomNumber(),
        fromTime, toTime, date);

    return result;
  }
}
