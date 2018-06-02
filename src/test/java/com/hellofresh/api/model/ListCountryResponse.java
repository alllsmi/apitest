package com.hellofresh.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "messages",
        "result"
})
public class ListCountryResponse {

    @JsonProperty("messages")
    private List<String> messages = null;
    @JsonProperty("result")
    private List<Country> country = null;

    @JsonProperty("messages")
    public List<String> getMessages() {
        return messages;
    }

    @JsonProperty("messages")
    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @JsonProperty("result")
    public List<Country> getCountry() {
        return country;
    }

    @JsonProperty("result")
    public void setCountry(List<Country> country) {
        this.country = country;
    }

}