package com.hellofresh.api.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hellofresh.api.model.Country;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class AddCountry extends BaseTest {

    @Test(description = "POST",dataProvider = "getAddCountry")
    public void addCountry(ArrayList<String> countryInfo) throws JsonProcessingException {
        Country country = new Country(countryInfo.get(0), countryInfo.get(1), countryInfo.get(2));
        Response response = addCountry(ADD_COUNTRY, country);
        verifyResponseCode(response, SUCCESSFUL_CODE);
    }
}
