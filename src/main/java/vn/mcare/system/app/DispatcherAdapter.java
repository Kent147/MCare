package vn.mcare.system.app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;
import vn.mcare.system.common.pojo.info.RequestLog;

@Slf4j
public class DispatcherAdapter extends DispatcherServlet {

  @Override
  protected void doDispatch(HttpServletRequest request, HttpServletResponse response)
      throws Exception {

    logRequest(request);
    if (!(request instanceof ContentCachingRequestWrapper)) {
      request = new ContentCachingRequestWrapper(request);
    }
    if (!(response instanceof ContentCachingResponseWrapper)) {
      response = new ContentCachingResponseWrapper(response);
    }
    //    HandlerExecutionChain handler = getHandler(request);
    try {
      super.doDispatch(request, response);
    } finally {
      log.info("Request payload: " + getRequestOayload(request));
      log.info("Response: " + getResponsePayload(response));
      updateResponse(response);
    }
  }

  private String getResponsePayload(HttpServletResponse response) {
    ContentCachingResponseWrapper wrapper =
        WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
    if (wrapper != null) {

      byte[] buf = wrapper.getContentAsByteArray();
      if (buf.length > 0) {
        int length = Math.min(buf.length, 5120);
        try {
          return new String(buf, 0, length, wrapper.getCharacterEncoding());
        } catch (UnsupportedEncodingException ex) {
          // NOOP
        }
      }
    }
    return null;
  }

  private String getRequestOayload(HttpServletRequest request) {
    ContentCachingRequestWrapper wrapper =
        WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
    if (wrapper != null) {

      byte[] buf = wrapper.getContentAsByteArray();
      if (buf.length > 0) {
        int length = Math.min(buf.length, 5120);
        try {
          return new String(buf, 0, length, wrapper.getCharacterEncoding()).replaceAll("\n", "");
        } catch (UnsupportedEncodingException ex) {
          // NOOP
        }
      }
    }
    return null;
  }

  private void updateResponse(HttpServletResponse response) throws IOException {
    ContentCachingResponseWrapper responseWrapper =
        WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
    responseWrapper.copyBodyToResponse();
  }

  private void logRequest(HttpServletRequest requestToCache) {
    log.info("Action: " + requestToCache.getRequestURI());

    String cif = requestToCache.getHeader("cif");
    String fullName = requestToCache.getHeader("fullName");
    String email = requestToCache.getHeader("email");
    String userAgent = requestToCache.getHeader("User-Agent");
    String token = requestToCache.getHeader("token");

    RequestLog reqlog =
        new RequestLog()
            .setMethod(requestToCache.getMethod())
            .setRequestUri(requestToCache.getRequestURI())
            .setRemoteAddress(requestToCache.getRemoteAddr())
            .setRemoteHost(requestToCache.getRemoteHost())
            .setRemotePort(requestToCache.getRemotePort())
            .setCif(cif)
            .setEmail(email)
            .setFullName(fullName)
            .setToken(token)
            .setContentType(requestToCache.getContentType())
            .setUserAgent(userAgent);

    log.info(reqlog.toString().replaceAll("\n", ""));
  }
}
