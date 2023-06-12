package com.example.cosmosservicecountries.controller;

import com.azure.spring.data.cosmos.core.query.CosmosPageRequest;
import com.example.cosmosservicecountries.model.Country;
import com.example.cosmosservicecountries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/countries")
    public List<Country> getCities() {
        final CosmosPageRequest pageRequest = new CosmosPageRequest(0, 100, null);

        Page<Country> page = countryService.findAll(pageRequest);
        List<Country> pageContent = page.getContent();

        while (page.hasNext()) {
            Pageable nextPageable = page.nextPageable();
            page = countryService.findAll(nextPageable);
            pageContent = page.getContent();
        }

        return pageContent;
    }
}
