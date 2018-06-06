package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Football.Country;
import pl.coderslab.sports_betting.Repository.CountryRepository;
import pl.coderslab.sports_betting.Service.Football.Service.CountryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CountryServiceImpl implements CountryService {
    @Autowired
    CountryRepository countryRepository;

    public void populateDb() {

        Country countryOne = new Country();
        countryOne.setName("Somewheria");

        Country countryTwo = new Country();
        countryTwo.setName("Randomia");

        List<Country> list = new ArrayList<>();
        list.add(countryOne);
        list.add(countryTwo);

        countryRepository.saveAll(list);
    }

    public List<Country> allCountries(){
        return countryRepository.findAll();
    }

    public Country getCountryById (Long countryId){
        Optional<Country> country = countryRepository.findById(countryId);
        Country country1 = new Country();
        if (country.isPresent()) {
            country1 = country.get();
        }
        return country1;
    }
}
