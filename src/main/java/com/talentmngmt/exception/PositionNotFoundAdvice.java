package com.talentmngmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PositionNotFoundAdvice {

  @ExceptionHandler(PositionNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String positionNotFoundHandler(PositionNotFoundException ex) {
    return ex.getMessage();
  }
}