package com.rest.regexp.interceptor;

import com.rest.regexp.model.QContact;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestInterceptor  extends HandlerInterceptorAdapter {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) throws Exception {

String parameter=request.getParameter("nameFilter");
System.out.println("========================"+parameter);
//    System.out.println("========================"+QContact.contact.name.matches("aaa"));

    return true;

  }
}
