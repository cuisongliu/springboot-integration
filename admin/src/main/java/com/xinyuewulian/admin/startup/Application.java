package com.xinyuewulian.admin.startup;

import com.cuisongliu.springboot.conf.SpringMvcConfig;
import com.xinyuewulian.admin.interceptor.LoginInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * Created by cuisongliu on 2016/10/24.
 *
 * @author cuisongliu
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xinyuewulian"})
@MapperScan(basePackages = "com.xinyuewulian.mapper")
public class Application extends SpringMvcConfig {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns("/error/**").excludePathPatterns("/").addPathPatterns("/**").excludePathPatterns("/portal/**");
    }

    @Override
    protected void configMultipart(MultipartConfigFactory multipartConfigFactory) {
    }

    @Override
    protected void setErrorPages(ConfigurableEmbeddedServletContainer container) {
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
        container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
        container.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
        container.addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
    }

}
