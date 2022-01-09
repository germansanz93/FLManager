package com.flmanager.feedlotservice.controller;

import com.flmanager.feedlotservice.exception.ProviderEmailExistsException;
import com.flmanager.feedlotservice.exception.ProviderIdNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ResponseBody
@ControllerAdvice
public class GlobalControllerAdvice {
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handlerValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ProviderEmailExistsException.class)
  public Map<String, String> handlerValidationException(ProviderEmailExistsException ex) {
    Map<String, String> errors = new HashMap<>();
    String fieldName = "Error: Invalid email";
    errors.put(fieldName, ex.getMessage());
    return errors;
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ProviderIdNotExistsException.class)
  public Map<String, String> handlerValidationException(ProviderIdNotExistsException ex) {
    Map<String, String> errors = new HashMap<>();
    String fieldName = "Error: Invalid id";
    errors.put(fieldName, ex.getMessage());
    return errors;
  }
}
