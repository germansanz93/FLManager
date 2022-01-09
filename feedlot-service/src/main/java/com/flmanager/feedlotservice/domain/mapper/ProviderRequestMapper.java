package com.flmanager.feedlotservice.domain.mapper;

import com.flmanager.feedlotservice.controller.request.ProviderRequest;
import com.flmanager.feedlotservice.domain.model.Provider;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProviderRequestMapper implements Function<ProviderRequest, Provider> {


  @Override
  public Provider apply(ProviderRequest providerRequest) {
    return Provider.builder()
      .name(providerRequest.getName())
      .lastName(providerRequest.getLastName())
      .address(providerRequest.getAddress())
      .phone(providerRequest.getPhone())
      .email(providerRequest.getEmail())
      .build();
  }

}
