package com.flmanager.feedlotservice.Service;

import com.flmanager.feedlotservice.Controller.Request.ProviderRequest;
import com.flmanager.feedlotservice.Controller.Response.ProviderResponse;
import com.flmanager.feedlotservice.Domain.Mapper.ProviderRequestMapper;
import com.flmanager.feedlotservice.Domain.Mapper.ProviderResponseMapper;
import com.flmanager.feedlotservice.Domain.Model.Provider;
import com.flmanager.feedlotservice.Repository.IProviderRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
class ProviderServiceTest {

  @Autowired
  public ProviderResponseMapper providerResponseMapper;

  @Autowired
  public ProviderRequestMapper providerRequestMapper;

  @InjectMocks
  public ProviderService providerService;

  @Mock
  public IProviderRepository providerRepository;

  @Before
  void setUp() {
    ProviderRequest providerRequest1 =
      ProviderRequest.builder()
        .name("Usuario")
        .lastName("uno")
        .address("Av. siempre viva 742")
        .email("usuario@uno.com")
        .phone("123123123")
        .build();

    ProviderRequest providerRequest2 =
      ProviderRequest.builder()
        .name("Usuario")
        .lastName("Dos")
        .address("Av. siempre viva 742")
        .email("usuario@dos.com")
        .phone("123123123")
        .build();

    ProviderRequest providerRequest3 =
      ProviderRequest.builder()
        .name("Usuario")
        .lastName("Tres")
        .address("Av. siempre viva 742")
        .email("usuario@tres.com")
        .phone("123123123")
        .build();

    ProviderRequest providerRequest4 =
      ProviderRequest.builder()
        .name("Usuario")
        .lastName("Cuatro")
        .address("Av. siempre viva 742")
        .email("usuario@cuatro.com")
        .phone("123123123")
        .build();

    ProviderRequest providerRequest5 =
      ProviderRequest.builder()
        .name("Usuario")
        .lastName("Cinco")
        .address("Av. siempre viva 742")
        .email("usuario@cinco.com")
        .phone("123123123")
        .build();

    List<ProviderRequest> providerRequestList = new ArrayList<>(Arrays.asList(providerRequest1, providerRequest2, providerRequest3, providerRequest4, providerRequest5));
    List<Provider> providerList = new ArrayList<>(providerRequestList.stream().map(providerRequest -> providerRequestMapper.apply(providerRequest))
      .collect(Collectors.toList()));
    providerList.forEach(provider -> provider.setIdProvider(UUID.randomUUID().toString()));
    List<ProviderResponse> providerResponseList = new ArrayList<>(providerList.stream().map(provider -> providerResponseMapper.apply(provider)).collect(Collectors.toList()));
  }

  @Test
  void getProviders() {
    when(IProviderRepository).ThenReturn
  }

  @Test
  void createProvider() {
  }

  @Test
  void getProvider() {
  }

  @Test
  void deleteProvider() {
  }

  @Test
  void updateProvider() {
  }
}