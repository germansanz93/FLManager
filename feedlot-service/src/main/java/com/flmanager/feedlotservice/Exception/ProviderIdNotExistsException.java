package com.flmanager.feedlotservice.Exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProviderIdNotExistsException extends RuntimeException {
  public ProviderIdNotExistsException(String message) {
    super(message);
    log.error("Id not found in DB");
  }
}