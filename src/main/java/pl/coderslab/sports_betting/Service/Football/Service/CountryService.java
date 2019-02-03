package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.Country;

import java.util.List;

public interface CountryService {
    void populateDb();
    List<Country> allCountries();
    Country getCountryById (Long countryId);
}
