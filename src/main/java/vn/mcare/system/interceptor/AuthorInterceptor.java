package vn.mcare.system.interceptor;

import com.google.gson.Gson;
import java.io.IOException;
import java.time.Instant;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.ERequestMethod;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.exception.AuthenticationException;
import vn.mcare.system.common.exception.AuthenticationException.InvalidTokenException;
import vn.mcare.system.common.exception.AuthenticationException.TokenExpiredException;
import vn.mcare.system.common.exception.SQLException.RecordNotFound;
import vn.mcare.system.common.pojo.dto.UserTokenDto;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.repository.intface.UserTokenRepo;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Component
public class AuthorInterceptor implements HandlerInterceptor {

  private final Gson gson;
  private final UserTokenRepo userTokenRepo;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws IOException {
    try {
      if (request.getMethod().equals(ERequestMethod.OPTIONS.getMethod())) {
        return true;
      }
      String token = request.getHeader("token");
      if (Objects.isNull(token)) {
        throw new AuthenticationException.InvalidTokenException("Token is null");
      }

      if (token.isEmpty()) {
        throw new AuthenticationException.InvalidTokenException("Token is empty");
      }

      // if token not exist will throw record not found exception
      UserTokenDto userTokenDto = userTokenRepo.getByToken(token);

      if (userTokenDto.getExpireDate() < Instant.now().toEpochMilli()) {
        throw new AuthenticationException.TokenExpiredException("Token expired");
      }

      return true;
    } catch (RecordNotFound e) {
      response.setHeader("Content-type", "application/json");
      response
          .getWriter()
          .write(
              getJsonResponse(
                  CodeResponse.UNAUTHORIZED.getMessage(), CodeResponse.UNAUTHORIZED.getCode()));
      return false;
    } catch (TokenExpiredException | InvalidTokenException e) {
      log.error(e.getMessage());
      response.setHeader("Content-type", "application/json");
      response
          .getWriter()
          .write(getJsonResponse(e.getMessage(), CodeResponse.BAD_REQUEST.getCode()));
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
      return false;
    }
  }

  private String getJsonResponse(String message, Integer codeResponse) {
    return gson.toJson(new RestfulFailureResponse().setMessages(message).setCode(codeResponse));
  }

}
