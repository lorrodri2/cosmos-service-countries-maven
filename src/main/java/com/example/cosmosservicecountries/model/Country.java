package com.example.cosmosservicecountries.model;

import com.azure.spring.data.cosmos.core.mapping.Container;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

@Container(containerName = "countries-container")
public class Country {

    @Id
    private String id;
    private String name;
    @Version
    private String _tag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String get_tag() {
        return _tag;
    }

    public void set_tag(String _tag) {
        this._tag = _tag;
    }
}