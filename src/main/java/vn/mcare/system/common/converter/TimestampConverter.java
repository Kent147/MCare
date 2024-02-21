package vn.mcare.system.common.converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.jooq.Converter;

public class TimestampConverter implements Converter<LocalDateTime, Long> {

  @Override
  public Long from(LocalDateTime databaseObject) {
    return databaseObject == null ? null : databaseObject.toEpochSecond(ZoneOffset.UTC);
  }

  @Override
  public LocalDateTime to(Long userObject) {
    return userObject == null ? null : new Timestamp(userObject).toLocalDateTime();
  }

  @Override
  public Class<LocalDateTime> fromType() {
    return LocalDateTime.class;
  }

  @Override
  public Class<Long> toType() {
    return Long.class;
  }
}
