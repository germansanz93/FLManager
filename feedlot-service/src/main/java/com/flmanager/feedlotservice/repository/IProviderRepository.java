package com.flmanager.feedlotservice.repository;

import com.flmanager.feedlotservice.domain.model.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends CrudRepository<Provider, String> {
}
