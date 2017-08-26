package com.rest.regexp.interceptor;

import org.springframework.http.HttpStatus;

/**
 * Represent exception info in response to the client
 */
public class ExceptionObject {

  private HttpStatus status;
  private String exceptionName;
  private String message;

  public ExceptionObject(HttpStatus status, String exceptionName, String message) {
    super();
    this.status = status;
    this.exceptionName = exceptionName;
    this.message = message;
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getExceptionName() {
    return exceptionName;
  }

  public void setExceptionName(String exceptionName) {
    this.exceptionName = exceptionName;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "ExceptionObject{" +
        "status=" + status +
        ", exceptionName='" + exceptionName + '\'' +
        ", message='" + message + '\'' +
        '}';
  }
}
