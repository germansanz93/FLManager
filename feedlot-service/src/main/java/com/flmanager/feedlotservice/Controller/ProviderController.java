package com.flmanager.feedlotservice.Controller;

import com.flmanager.feedlotservice.Controller.Request.ProviderRequest;
import com.flmanager.feedlotservice.Controller.Response.ProviderResponse;
import com.flmanager.feedlotservice.Domain.Mapper.ProviderRequestMapper;
import com.flmanager.feedlotservice.Domain.Model.Provider;
import com.flmanager.feedlotservice.Service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/provider")
@RestController
public class ProviderController {

  @Autowired
  public ProviderService providerService;

  @GetMapping
  public List<ProviderResponse> getProviders() {
    log.info("Get all providers request");
    return providerService.getProviders();
  }

  @PostMapping
  public ProviderResponse createProvider(@RequestBody @Validated ProviderRequest providerRequest) {
    log.info("Create provider request with data: ");
    return providerService.createProvider(providerRequest);
  }
}
