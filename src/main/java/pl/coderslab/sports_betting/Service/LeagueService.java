package pl.coderslab.sports_betting.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Country;
import pl.coderslab.sports_betting.Entity.League;
import pl.coderslab.sports_betting.Repository.CountryRepository;
import pl.coderslab.sports_betting.Repository.LeagueRepository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LeagueService {
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    LeagueRepository leagueRepository;

    public void populateDb() {

        League leagueOne = new League();
        leagueOne.setName("Somewhership");
        leagueOne.setCountry(countryRepository.findOneByName("Somewheria"));

        League leagueTwo = new League();
        leagueTwo.setName("Randomship");
        leagueTwo.setCountry(countryRepository.findOneByName("Randomia"));

        List<League> list = new ArrayList<>();
        list.add(leagueOne);
        list.add(leagueTwo);

        leagueRepository.saveAll(list);
    }

    public List<League> allLeagues(){
        return leagueRepository.findAll();
    }

    public List<League> findLeagueByCountryId(Long countryID){
        return leagueRepository.findAllByCountry_Id(countryID);
    }

    public League findLeagueById(Long leagueID){
        Optional<League> league = leagueRepository.findById(leagueID);
        League league1 = new League();
        if (league.isPresent()) {
            league1 = league.get();
        }
        return league1;
    }
}
