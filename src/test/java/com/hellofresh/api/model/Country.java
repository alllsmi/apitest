package com.hellofresh.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "alpha2_code",
        "alpha3_code"
})
public class Country {

    @JsonProperty("name")
    private String name;
    @JsonProperty("alpha2_code")
    private String alpha2Code;
    @JsonProperty("alpha3_code")
    private String alpha3Code;

    public Country(){
    }

    public Country(String name, String alpha2Code, String alpha3Code){
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("alpha2_code")
    public String getAlpha2Code() {
        return alpha2Code;
    }

    @JsonProperty("alpha2_code")
    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    @JsonProperty("alpha3_code")
    public String getAlpha3Code() {
        return alpha3Code;
    }

    @JsonProperty("alpha3_code")
    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

}