package com.flmanager.feedlotservice.Domain.Mapper;

import com.flmanager.feedlotservice.Controller.Response.ProviderResponse;
import com.flmanager.feedlotservice.Domain.Model.Provider;
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
