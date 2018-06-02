package com.hellofresh.api.test;

import com.hellofresh.api.model.ListCountry;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class CountryPresence extends BaseTest {

    @Test(description = "GET",dataProvider = "getCountriesPresence")
    public void countryPresence(List<String> countries) throws IOException {
        ListCountry listCountry = getListCountry(URL+ALL_SUFFIX);
        for (String country : countries) {
            verifyCountryPresented(listCountry, country);
        }
    }
}
