package com.hellofresh.api.test;

import com.hellofresh.api.model.SingleCountry;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;

public class NonexistentCountries extends BaseTest {

    @Test(description = "GET",dataProvider = "getNonexistentCountry")
    public void nonexistentCountries(ArrayList<String> countryInfo) throws IOException {
        SingleCountry singleCountryResponse = getSingleCountryResponse(URL +
                String.format(COUNTRY_SUFFIX_PATTERN, countryInfo.get(0)));
        validateNonexistentCountry(singleCountryResponse, countryInfo.get(0));
    }
}
