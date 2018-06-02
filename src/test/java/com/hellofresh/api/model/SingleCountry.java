package com.hellofresh.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "RestResponse"
})
public class SingleCountry {

    @JsonProperty("RestResponse")
    private SingleCountryResponse singleCountryResponse;

    @JsonProperty("RestResponse")
    public SingleCountryResponse getSingleCountryResponse() {
        return singleCountryResponse;
    }

    @JsonProperty("RestResponse")
    public void setSingleCountryResponse(SingleCountryResponse singleCountryResponse) {
        this.singleCountryResponse = singleCountryResponse;
    }

}