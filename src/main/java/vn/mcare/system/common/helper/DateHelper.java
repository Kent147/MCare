package vn.mcare.system.common.helper;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import vn.mcare.system.common.constant.enums.EDatePattern;

public class DateHelper {

  public static LocalDate toLocalDate(Long milliseconds) {
    LocalDate localDate = Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault())
            .toLocalDate();
    return localDate;
  }

  public static LocalDate toLocalDate(Date date) {
    LocalDate localDate = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault())
            .toLocalDate();
    return localDate;
  }

  public static LocalDateTime toLocalDateTime(Long milliseconds) {
    LocalDateTime localDateTime = Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    return localDateTime;
  }

  public static LocalDateTime toStartLocalDateTime(Long milliseconds) {
    LocalDateTime localDateTime = Instant
            .ofEpochMilli(milliseconds)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .withHour(0)
            .withMinute(0)
            .withSecond(0);
    return localDateTime;
  }

  public static LocalDateTime toEndLocalDateTime(Long milliseconds) {
    LocalDateTime localDateTime = Instant
            .ofEpochMilli(milliseconds)
            .atZone(ZoneId.systemDefault())
            .toLocalDateTime()
            .withHour(23)
            .withMinute(59)
            .withSecond(59);
    return localDateTime;
  }

  public static LocalDateTime toLocalDateTime(Date date) {
    LocalDateTime localDateTime = Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault())
            .toLocalDateTime();
    return localDateTime;
  }

  public static Long toMilliseconds(LocalDateTime dateTime) {
    return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }

  public static Long toMilliseconds(LocalDate dateTime) {
    return dateTime.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
  }

  public static Long toMilliseconds(Long longTime) {
    return toLocalDate(longTime).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
  }

  public static Long toUnixTimeMySQL(Long longTime) {
    return ZonedDateTime.of(DateHelper.toLocalDateTime(longTime), ZoneId.systemDefault()).toEpochSecond();
  }

  public static String convertToString(EDatePattern pattern, Long dateTime) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern.getVal());
    return sdf.format(dateTime);
  }
}
