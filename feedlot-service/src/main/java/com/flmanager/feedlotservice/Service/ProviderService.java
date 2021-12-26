package com.flmanager.feedlotservice.Service;

import com.flmanager.feedlotservice.Controller.Response.ProviderResponse;
import com.flmanager.feedlotservice.Domain.Mapper.ProviderResponseMapper;
import com.flmanager.feedlotservice.Domain.Model.Provider;
import com.flmanager.feedlotservice.Repository.IProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderService {

  @Autowired
  private IProviderRepository providerRepository;

  @Autowired
  private ProviderResponseMapper providerResponseMapper;

  public List<ProviderResponse> getProviders() {
    List<ProviderResponse> providerResponseList = new ArrayList<>();
    providerRepository.findAll().forEach(provider -> providerResponseList.add(providerResponseMapper.apply(provider)));
    return providerResponseList;
  }
}
