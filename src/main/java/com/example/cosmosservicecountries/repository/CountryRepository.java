package com.example.cosmosservicecountries.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.cosmosservicecountries.model.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CosmosRepository<Country, String> {
}
