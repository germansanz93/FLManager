package com.flmanager.feedlotservice.domain.mapper;

import com.flmanager.feedlotservice.controller.response.ProviderResponse;
import com.flmanager.feedlotservice.domain.model.Provider;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProviderResponseMapper implements Function<Provider, ProviderResponse> {

  @Override
  public ProviderResponse apply(Provider provider) {
    return ProviderResponse.builder()
      .idProvider(provider.getIdProvider())
      .name(provider.getName())
      .lastName(provider.getLastName())
      .address(provider.getAddress())
      .phone(provider.getPhone())
      .email(provider.getEmail())
      .build();
  }
}
