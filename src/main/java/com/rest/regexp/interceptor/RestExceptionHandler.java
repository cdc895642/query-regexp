package com.rest.regexp.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    return new ResponseEntity<>(
        new ExceptionObject(
            HttpStatus.BAD_REQUEST,
            ex.getClass().getName(),
            ex.getMessage()),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({com.querydsl.core.QueryException.class})
  public ResponseEntity<Object> QueryArgumentException(final Exception ex) {
    return new ResponseEntity<>(
        new ExceptionObject(
            HttpStatus.BAD_REQUEST,
            ex.getClass().getName(),
            ex.getMessage()),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }
}
