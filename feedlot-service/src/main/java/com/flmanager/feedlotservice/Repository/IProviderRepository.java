package com.flmanager.feedlotservice.Repository;

import com.flmanager.feedlotservice.Domain.Model.Provider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends CrudRepository<Provider, String> {
}
