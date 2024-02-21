package vn.mcare.system;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import vn.mcare.system.app.DispatcherAdapter;
import vn.mcare.system.common.constant.config.ModuleConfig;
import vn.mcare.system.common.helper.PdfHelper;

@Slf4j
@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
public class App {

    public static void main(String args[]) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public ModuleConfig loadConfig() {
        try {
            BufferedReader bufferedReader =
                    new BufferedReader(
                            //          new
                            // InputStreamReader(getClass().getResourceAsStream("/team-system-config.json")));
                            new InputStreamReader(getClass().getResourceAsStream("/local-config.json")));
            String readFile = "";
            while (bufferedReader.ready()) {
                readFile += bufferedReader.readLine();
            }
            ModuleConfig moduleConfig = new Gson().fromJson(readFile, ModuleConfig.class);
            bufferedReader.close();
            return moduleConfig;
        } catch (Exception e) {
            throw new RuntimeException("Cant load config file");
        }
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(10000000);
        return commonsMultipartResolver;
    }


    @Bean
    public ServletRegistrationBean dispatcherRegistration() {
        return new ServletRegistrationBean(dispatcherServlet());
    }

    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherAdapter();
    }
}
