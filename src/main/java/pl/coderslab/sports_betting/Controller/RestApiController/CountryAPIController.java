package pl.coderslab.sports_betting.Controller.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Country;
import pl.coderslab.sports_betting.Service.CountryService;

import java.util.List;

@RestController
@RequestMapping("/api/country")
public class CountryAPIController {

    @Autowired
    CountryService countryService;

    @RequestMapping(path = "/all")
    public	List<Country> sample() {
        return	countryService.allCountries();
    }

    // should be sth like in API Footbal, exact id = exact country, match, league etc
}
