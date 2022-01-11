package com.flmanager.feedlotservice.service;

import com.flmanager.feedlotservice.controller.request.ProviderRequest;
import com.flmanager.feedlotservice.controller.response.ProviderResponse;
import com.flmanager.feedlotservice.domain.mapper.ProviderRequestMapper;
import com.flmanager.feedlotservice.domain.mapper.ProviderResponseMapper;
import com.flmanager.feedlotservice.domain.model.Provider;
import com.flmanager.feedlotservice.exception.ProviderEmailExistsException;
import com.flmanager.feedlotservice.exception.ProviderIdNotExistsException;
import com.flmanager.feedlotservice.repository.IProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProviderService {

  @Autowired
  private IProviderRepository providerRepository;

  @Autowired
  private ProviderResponseMapper providerResponseMapper;

  @Autowired
  private ProviderRequestMapper providerRequestMapper;

  public List<ProviderResponse> getProviders() {
    List<ProviderResponse> providerResponseList = new ArrayList<>();
    providerRepository.findAll().forEach(provider -> providerResponseList.add(providerResponseMapper.apply(provider)));
    return providerResponseList;
  }

  public ProviderResponse createProvider(ProviderRequest providerRequest) {
    try {
      return providerResponseMapper.apply(providerRepository.save(providerRequestMapper.apply(providerRequest)));
    } catch (DataIntegrityViolationException ex) {
      throw new ProviderEmailExistsException("Email already taken");
    }
  }

  public ProviderResponse getProvider(String idProvider) {
    return providerResponseMapper.apply(providerRepository.findById(idProvider).orElseThrow(() -> new ProviderIdNotExistsException("Provider id not found")));
  }

  public void deleteProvider(String idProvider) {
    try {
      providerRepository.deleteById(idProvider);
    } catch (EmptyResultDataAccessException ex) {
      throw new ProviderIdNotExistsException("Provider id not found");
    }
  }

  public ProviderResponse updateProvider(String idProvider, ProviderRequest providerRequest){
    if(providerRepository.existsById(idProvider)){
      try {
        Provider provider = providerRequestMapper.apply(providerRequest);
        provider.setIdProvider(idProvider);
        return providerResponseMapper.apply(providerRepository.save(provider));
      } catch (DataIntegrityViolationException ex) {
          throw new ProviderEmailExistsException("Email already taken");
      }
    } else {
      throw new ProviderIdNotExistsException("Provider id not found");
    }
  }
}
