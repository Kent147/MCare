package vn.mcare.system.common.tool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class Generator {

  public static String generate() {
    UUID idGenerate = new UUID();
    log.debug(idGenerate.toString().replaceAll("-", ""));
    return idGenerate.toString().replaceAll("-", "");
  }

  public static String genRandomNum() {
    Long randomNum = Math.round(Math.random() * 1000);
    String result = StringUtils.leftPad(randomNum.toString(), 3, "0");
    return result;
  }
}
