package com.nexusll.ideasgenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ApiData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String url;
    private String apiName;

    public ApiData(){}

    public ApiData(String apiName, String description, String url) {
        this.description = description;
        this.url = url;
        this.apiName = apiName;
    }

    public String toString(){
        return "<<" + this.apiName + "-- " + this.description + ">>";
    }
}
