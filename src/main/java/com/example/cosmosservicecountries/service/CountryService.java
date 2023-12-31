package com.example.cosmosservicecountries.service;

import com.example.cosmosservicecountries.model.Country;
import com.example.cosmosservicecountries.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public Page<Country> findAll(Pageable pageable) {
        return countryRepository.findAll(pageable);
    }
}
