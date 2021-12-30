package com.flmanager.feedlotservice.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProviderEmailExistsException extends RuntimeException {
  public ProviderEmailExistsException (String message){
    super(message);
    log.error("Email already exists in DB");
  }
}
