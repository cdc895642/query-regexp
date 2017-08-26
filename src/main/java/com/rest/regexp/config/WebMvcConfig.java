package com.rest.regexp.config;

import com.rest.regexp.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
  @Override
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**");
  }
}
