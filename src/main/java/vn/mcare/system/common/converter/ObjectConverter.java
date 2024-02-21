package vn.mcare.system.common.converter;

import com.google.gson.Gson;
import org.jooq.Record;

public class ObjectConverter {

  private static final Gson gson = new Gson();

  public static <T> T convertToDto(Object value, Class<T> type) {
    return gson.fromJson(gson.toJson(value), type);
  }
}
