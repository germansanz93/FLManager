package com.flmanager.feedlotservice.service;

import com.flmanager.feedlotservice.controller.request.ProviderRequest;
import com.flmanager.feedlotservice.controller.response.ProviderResponse;
import com.flmanager.feedlotservice.domain.mapper.ProviderRequestMapper;
import com.flmanager.feedlotservice.domain.mapper.ProviderResponseMapper;
import com.flmanager.feedlotservice.domain.model.Provider;
import com.flmanager.feedlotservice.exception.ProviderEmailExistsException;
import com.flmanager.feedlotservice.exception.ProviderIdNotExistsException;
import com.flmanager.feedlotservice.repository.IProviderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class
ProviderServiceTest {
  @InjectMocks
  @Spy
  public ProviderService providerService;

  @Mock
  public IProviderRepository providerRepository;

  @Mock
  public ProviderResponseMapper providerResponseMapper;

  @Mock
  public ProviderRequestMapper providerRequestMapper;

  Provider provider1 =
    Provider.builder()
      .idProvider("0648bedf-d5f8-4a1c-9525-9139ab9dbd91")
      .name("Usuario")
      .lastName("uno")
      .address("Av. siempre viva 742")
      .email("usuario@uno.com")
      .phone("123123123")
      .build();

  Provider provider2 =
    Provider.builder()
      .idProvider("0ee29892-4dff-4f5f-8b38-eb90d5e2690c")
      .name("Usuario")
      .lastName("Dos")
      .address("Av. siempre viva 742")
      .email("usuario@dos.com")
      .phone("123123123")
      .build();

  Provider provider3 =
    Provider.builder()
      .idProvider("1d134ad8-cb94-431c-91d4-b076188f6dc1")
      .name("Usuario")
      .lastName("Tres")
      .address("Av. siempre viva 742")
      .email("usuario@tres.com")
      .phone("123123123")
      .build();

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

  ProviderResponse providerResponse1 =
    ProviderResponse.builder()
      .idProvider("0648bedf-d5f8-4a1c-9525-9139ab9dbd91")
      .name("Usuario")
      .lastName("uno")
      .address("Av. siempre viva 742")
      .email("usuario@uno.com")
      .phone("123123123")
      .build();

  ProviderResponse providerResponse2 =
    ProviderResponse.builder()
      .idProvider("0ee29892-4dff-4f5f-8b38-eb90d5e2690c")
      .name("Usuario")
      .lastName("Dos")
      .address("Av. siempre viva 742")
      .email("usuario@dos.com")
      .phone("123123123")
      .build();

  ProviderResponse providerResponse3 =
    ProviderResponse.builder()
      .idProvider("1d134ad8-cb94-431c-91d4-b076188f6dc1")
      .name("Usuario")
      .lastName("Tres")
      .address("Av. siempre viva 742")
      .email("usuario@tres.com")
      .phone("123123123")
      .build();

  @Test
  public void getProviders() {
    when(providerRepository.findAll()).thenReturn(Arrays.asList(provider1, provider2, provider3));
    when(providerResponseMapper.apply(provider1)).thenCallRealMethod();
    when(providerResponseMapper.apply(provider2)).thenCallRealMethod();
    when(providerResponseMapper.apply(provider3)).thenCallRealMethod();
    assertEquals(Arrays.asList(providerResponse1, providerResponse2, providerResponse3), providerService.getProviders());
  }

  @Test
  public void createProviderSuccess() {
    when(providerRequestMapper.apply(any(ProviderRequest.class))).thenCallRealMethod();
    when(providerRepository.save(providerRequestMapper.apply(providerRequest1))).thenReturn(provider1);
    when(providerResponseMapper.apply(any(Provider.class))).thenCallRealMethod();
    assertEquals(providerResponse1, providerService.createProvider(providerRequest1));
  }

  @Test
  public void createProviderFailByProviderEmailExistsException() {
    when(providerRequestMapper.apply(providerRequest1)).thenCallRealMethod();
    when(providerRepository.save(any(Provider.class))).thenThrow(DataIntegrityViolationException.class);
    assertThrows(ProviderEmailExistsException.class, () -> providerService.createProvider(providerRequest1));
  }

  @Test
  public void getProviderSuccess() {
    when(providerRepository.findById(provider1.getIdProvider())).thenReturn(Optional.of(provider1));
    when(providerResponseMapper.apply(any(Provider.class))).thenCallRealMethod();
    assertEquals(providerService.getProvider(provider1.getIdProvider()), providerResponse1);
  }

  @Test
  public void getProviderFailByProviderIdNotExists() {
    when(providerRepository.findById(provider1.getIdProvider())).thenThrow(ProviderIdNotExistsException.class);
    assertThrows(ProviderIdNotExistsException.class, () -> providerService.getProvider(provider1.getIdProvider()));
  }

  @Test
  public void deleteProviderSuccess() {
    doNothing().when(providerRepository).deleteById(provider1.getIdProvider());
    providerRepository.deleteById(provider1.getIdProvider());
    verify(providerRepository, times(1)).deleteById(provider1.getIdProvider());
  }

  @Test
  public void deleteProviderFailByProviderIdNotExists() {
    doThrow(EmptyResultDataAccessException.class).when(providerRepository).deleteById(provider1.getIdProvider());
    assertThrows(ProviderIdNotExistsException.class, () -> providerService.deleteProvider(provider1.getIdProvider()));
  }

  @Test
  public void updateProviderSuccess() {
    when(providerRepository.existsById(provider1.getIdProvider())).thenReturn(true);
    when(providerRequestMapper.apply(providerRequest1)).thenCallRealMethod();
    when(providerRepository.save(provider1)).thenReturn(provider1);
    when(providerResponseMapper.apply(provider1)).thenCallRealMethod();
    assertEquals(providerResponse1, providerService.updateProvider(provider1.getIdProvider(), providerRequest1));
  }

  @Test
  public void updateProviderFailByProviderIdNotExists() {
    when(providerRepository.existsById(provider1.getIdProvider())).thenReturn(false);
    assertThrows(ProviderIdNotExistsException.class, () -> providerService.updateProvider(provider1.getIdProvider(), providerRequest1));
  }

  @Test
  public void updateProviderFailByProviderEmailExists() {
    when(providerRepository.existsById(provider1.getIdProvider())).thenReturn(true);
    when(providerRequestMapper.apply(any(ProviderRequest.class))).thenCallRealMethod();
    when(providerRepository.save(any(Provider.class))).thenThrow(DataIntegrityViolationException.class);
    assertThrows(ProviderEmailExistsException.class, () -> providerService.updateProvider(provider1.getIdProvider() ,providerRequest1));
  }
}