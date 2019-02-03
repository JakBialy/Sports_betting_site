package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Football.Country;
import pl.coderslab.sports_betting.Repository.General.CountryRepository;
import pl.coderslab.sports_betting.Service.Football.Service.CountryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /**
     * Method populate database with countries and save them to
     * database
     */
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

    /**
     * Sends back all countries from database
     */
    public List<Country> allCountries(){
        return countryRepository.findAll();
    }

    /**
     * After input of country id gives back country
     * @param countryId parameter of selected country
     * @return returns selected countries
     */

    public Country getCountryById (Long countryId){
        return countryRepository.getOne(countryId);
    }
}
