package com.hellofresh.api.test;

import static io.restassured.RestAssured.get;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hellofresh.api.model.ListCountry;
import com.hellofresh.api.model.Country;
import com.hellofresh.api.model.SingleCountry;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    static final String URL = "http://services.groupkt.com/country/get/";
    static final String ALL_SUFFIX = "all";
    static final String COUNTRY_SUFFIX_PATTERN = "iso2code/%s";
    static final String ADD_COUNTRY = "http://services.groupkt.com/country/post/";
    private static final String NONEXISTENT_COUNTRY_MESSAGE_PATTERN = "No matching country found for requested code [%s].";
    static final int SUCCESSFUL_CODE = 200;

    ListCountry getListCountry(String url) throws IOException {
        log.info("Getting countries JSON.");
        Response response = get(url);
        verifyResponseCode(response, SUCCESSFUL_CODE);
        JSONObject jsonResponse = new JSONObject(response.asString());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse.toString(), ListCountry.class);
    }

    SingleCountry getSingleCountryResponse(String url) throws IOException {
        log.info("Getting single country JSON.");
        Response response = get(url);
        verifyResponseCode(response, SUCCESSFUL_CODE);
        JSONObject jsonResponse = new JSONObject(response.asString());
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse.toString(), SingleCountry.class);
    }

    Response addCountry(String url, Country country) throws JsonProcessingException {
        log.info("Adding country: " + country.getAlpha2Code());
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonRequest = new JSONObject(objectMapper.writeValueAsString(country));
        RequestSpecification request = RestAssured.given();
        request.body(jsonRequest.toString());
        return request.post(ADD_COUNTRY);
    }

    void verifyCountryPresented(ListCountry listCountry, String country){
        log.info("Verify country presented: " + country);
        List<Country> countries = listCountry.getListCountryResponse().getCountry();
        int occurs = 0;
        for (Country tempCountry : countries) {
            if (tempCountry.getAlpha2Code().equals(country)) {
                occurs++;
            }
        }
        Assert.assertEquals(occurs, 1, country + " Country presence is not equals to 1");
    }
    void validateCountry(SingleCountry singleCountry, String name, String alpha2_code, String alpha3_code){
        log.info("Validate country: " + alpha2_code);
        Assert.assertEquals(singleCountry.getSingleCountryResponse().getCountry().getName(), name);
        Assert.assertEquals(singleCountry.getSingleCountryResponse().getCountry().getAlpha2Code(), alpha2_code);
        Assert.assertEquals(singleCountry.getSingleCountryResponse().getCountry().getAlpha3Code(), alpha3_code);
    }

    void validateNonexistentCountry(SingleCountry singleCountry, String alpha2_code){
        log.info("Validate nonexistent country: " + alpha2_code);
        Assert.assertEquals(singleCountry.getSingleCountryResponse().getMessages().get(0),
                String.format(NONEXISTENT_COUNTRY_MESSAGE_PATTERN, alpha2_code));
    }

    void verifyResponseCode(Response response, int code){
        log.info("Validate response code: " + code);
        Assert.assertEquals(response.getStatusCode(), code);
    }

    @DataProvider(name = "getCountriesPresence")
    public Object[][] getCountriesPresence() {
        return getData("testData/countryPresence.csv");
    }

    @DataProvider(name = "getValidateCountry")
    public Object[][] getValidateCountry() {
        return getData("testData/validateCountry.csv");
    }

    @DataProvider(name = "getAddCountry")
    public Object[][] getAddCountry() {
        return getData("testData/addCountry.csv");
    }

    @DataProvider(name = "getNonexistentCountry")
    public Object[][] getNonexistentCountry() {
        return getData("testData/nonexistentCountry.csv");
    }

    private Object[][] getData(String fileName){
        ArrayList<ArrayList<String>> csvData = parseDataCsv(fileName);
        Object[][] testData = new Object[csvData.size()][1];
        for (int i = 0; i < csvData.size(); i++){
            testData[i][0] = csvData.get(i);
        }
        return testData;
    }

    private ArrayList<ArrayList<String>> parseDataCsv(String fileName) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        String line;
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(this
                        .getClass().getResourceAsStream("/"+ fileName)))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                result.add(new ArrayList<>(Arrays.asList(values)));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }
}
