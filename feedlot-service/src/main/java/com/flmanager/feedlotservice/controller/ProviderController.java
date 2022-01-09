package com.flmanager.feedlotservice.controller;

import com.flmanager.feedlotservice.controller.request.ProviderRequest;
import com.flmanager.feedlotservice.controller.response.ProviderResponse;
import com.flmanager.feedlotservice.service.ProviderService;
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
    log.info("Request: Get all providers");
    return providerService.getProviders();
  }

  @PostMapping
  public ProviderResponse createProvider(@RequestBody @Validated ProviderRequest providerRequest) {
    log.info("Request: Create provider");
    return providerService.createProvider(providerRequest);
  }

  @GetMapping("/{idProvider}")
  public ProviderResponse getProvider(@PathVariable(name = "idProvider") String idProvider){
    log.info("Request: Get one provider");
    return providerService.getProvider(idProvider);
  }

  @DeleteMapping("/{idProvider}")
  public void deleteProvider(@PathVariable(name = "idProvider") String idProvider){
    log.info("Request: Delete one provider");
    providerService.deleteProvider(idProvider);
  }

  @PutMapping("/{idProvider}")
  public ProviderResponse updateProvider(@PathVariable(name = "idProvider") String idProvider,
                                         @RequestBody @Validated ProviderRequest providerRequest){
    log.info("Request: update provider");
    return providerService.updateProvider(idProvider, providerRequest);
  }

}
