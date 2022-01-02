package com.flmanager.feedlotservice.Service;

import com.flmanager.feedlotservice.Controller.Request.ProviderRequest;
import com.flmanager.feedlotservice.Controller.Response.ProviderResponse;
import com.flmanager.feedlotservice.Domain.Mapper.ProviderRequestMapper;
import com.flmanager.feedlotservice.Domain.Mapper.ProviderResponseMapper;
import com.flmanager.feedlotservice.Exception.ProviderEmailExistsException;
import com.flmanager.feedlotservice.Exception.ProviderIdNotExistsException;
import com.flmanager.feedlotservice.Repository.IProviderRepository;
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
}
