package pl.coderslab.sports_betting.Controller.RestApiController.Football;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Football.Country;
import pl.coderslab.sports_betting.Service.Football.Service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/fbCountry")
public class CountryAPIController {

    @Autowired
    CountryService countryService;

    @GetMapping(path = "/all")
    public	List<Country> getAllCountries() {
        return	countryService.allCountries();
    }

    /*
    countries: 3,4
     */
    @GetMapping(path = "/{id}")
    public	Country getCountryById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }
}
