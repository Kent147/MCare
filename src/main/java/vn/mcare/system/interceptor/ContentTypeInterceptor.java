package vn.mcare.system.interceptor;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.exception.AuthenticationException.InvalidRequestException;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentTypeInterceptor implements HandlerInterceptor {

  private final Gson gson;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
          throws Exception {
    try {
      response.setHeader("Content-type", "application/json;charset=utf8");
      String contentType = request.getHeader("Content-type");

      if (contentType != null) {
//        if (!contentType.contains("application/json")) {
//          throw new AuthenticationException.InvalidRequestException(
//                  "Only accept content type application/json");
//        }
      }

      return true;
    } catch (InvalidRequestException e) {
      log.error(e.getMessage());
      response.setHeader("Content-type", "application/json");
      response
              .getWriter()
              .write(
                      getJsonResponse(e.getMessage(), CodeResponse.BAD_REQUEST.getCode()));
      getRequestPayload(request);
      return false;
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      response.setHeader("Content-type", "application/json");
      response
              .getWriter()
              .write(
                      getJsonResponse(
                              CodeResponse.INTERNAL_SERVER.getMessage(),
                              CodeResponse.INTERNAL_SERVER.getCode()));
      getRequestPayload(request);
      return false;
    }
  }

  private String getJsonResponse(String message, Integer codeResponse) {
    return gson.toJson(new RestfulFailureResponse().setMessages(message).setCode(codeResponse));
  }

  private String getRequestPayload(HttpServletRequest request) throws IOException {
    StringBuffer stringBuffer = new StringBuffer();
    request.getReader().lines().forEach(stringBuffer::append);
    return "Request payload at ContentTypeInterceptor: " + stringBuffer.toString();
  }
}
