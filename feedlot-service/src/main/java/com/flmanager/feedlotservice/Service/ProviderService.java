package com.flmanager.feedlotservice.Service;

import com.flmanager.feedlotservice.Domain.Model.Provider;
import com.flmanager.feedlotservice.Repository.IProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderService {

  @Autowired
  private IProviderRepository providerRepository;

  public List<Provider> getProviders() {
    return Streamable.of(providerRepository.findAll()).toList();
  }
}
