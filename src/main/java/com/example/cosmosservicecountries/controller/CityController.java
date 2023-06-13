package com.example.cosmosservicecountries.controller;

import com.azure.spring.data.cosmos.core.query.CosmosPageRequest;
import com.example.cosmosservicecountries.kafka.KafkaConfig;
import com.example.cosmosservicecountries.model.Country;
import com.example.cosmosservicecountries.serializer.Message;
import com.example.cosmosservicecountries.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CityController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private KafkaConfig kafkaConfig;

    @GetMapping("/countries")
    public Map<String, Object> getCountries(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize, @RequestParam String message) {
        pageSize = (pageSize != null) ? pageSize : 100;
        pageNumber = (pageNumber != null) ? pageNumber : 0;
        message = (message != null) ? message : "";

        Map<String, Object> result = new HashMap<>();

        final CosmosPageRequest pageRequest = new CosmosPageRequest(pageNumber, pageSize, null);

        Page<Country> page = countryService.findAll(pageRequest);
        List<Country> pageContent = page.getContent();

        result.put("countries", pageContent);
        result.put("messages", kafkaConfig.getMessages());

        Message messageToSend = new Message();
        messageToSend.setText(message);
        kafkaConfig.send(messageToSend);

        return result;
    }
}
