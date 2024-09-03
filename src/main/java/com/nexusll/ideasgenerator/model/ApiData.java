package com.nexusll.ideasgenerator.model;

import jakarta.persistence.*;

@Entity
@Table(name = "api_data")
public class ApiData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "apiname")
    private String apiName;

    public ApiData() {}

    public ApiData(String apiName, String description, String url) {
        this.description = description;
        this.url = url;
        this.apiName = apiName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String toString(){
        return "<<" + this.apiName + "-- " + this.description + ">>";
    }
}
