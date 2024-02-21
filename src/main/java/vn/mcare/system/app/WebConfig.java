package vn.mcare.system.app;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vn.mcare.system.common.constant.ServerURL.User;
import vn.mcare.system.interceptor.AuthorInterceptor;
import vn.mcare.system.interceptor.ContentTypeInterceptor;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebConfig implements WebMvcConfigurer {

  private final AuthorInterceptor authorInterceptor;
  private final ContentTypeInterceptor contentTypeInterceptor;

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins("*")
        .allowedMethods("*");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
//    registry.addInterceptor(contentTypeInterceptor);
    registry
        .addInterceptor(authorInterceptor)
        .excludePathPatterns(User.USER_LOGIN, User.CREATE);
  }
}
