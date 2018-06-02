package com.hellofresh.api.test;

import com.hellofresh.api.model.SingleCountry;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;

public class CountryValidation extends BaseTest {

    @Test(description = "GET",dataProvider = "getValidateCountry")
    public void validateCountry(ArrayList<String> countryInfo) throws IOException {
        SingleCountry singleCountryResponse = getSingleCountryResponse(URL +
                String.format(COUNTRY_SUFFIX_PATTERN, countryInfo.get(1)));
        validateCountry(singleCountryResponse, countryInfo.get(0), countryInfo.get(1), countryInfo.get(2));
    }
}
