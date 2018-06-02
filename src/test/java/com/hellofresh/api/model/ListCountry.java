package com.hellofresh.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "RestResponse"
})
public class ListCountry {

    @JsonProperty("RestResponse")
    private ListCountryResponse listCountryResponse;

    @JsonProperty("RestResponse")
    public ListCountryResponse getListCountryResponse() {
        return listCountryResponse;
    }

    @JsonProperty("RestResponse")
    public void setListCountryResponse(ListCountryResponse listCountryResponse) {
        this.listCountryResponse = listCountryResponse;
    }

}