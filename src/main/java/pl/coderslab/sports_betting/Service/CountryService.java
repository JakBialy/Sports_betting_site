package pl.coderslab.sports_betting.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Country;
import pl.coderslab.sports_betting.Repository.CountryRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryService {
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

}
