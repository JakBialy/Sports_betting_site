package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.Country;

import java.util.List;

public interface CountryService {

    public void populateDb();

    public List<Country> allCountries();

    public Country getCountryById (Long countryId);

    }
