package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Football.FootballLeague;
import pl.coderslab.sports_betting.Repository.CountryRepository;
import pl.coderslab.sports_betting.Repository.Fotball.FootballLeagueRepository;
import pl.coderslab.sports_betting.Service.Football.Service.FootballBetService;
import pl.coderslab.sports_betting.Service.Football.Service.FootballLeagueService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FootballLeagueServiceImpl implements FootballLeagueService {
    @Autowired
    CountryRepository countryRepository;

    @Autowired
    FootballLeagueRepository footballLeagueRepository;

    public void populateDb() {

        FootballLeague footballLeagueOne = new FootballLeague();
        footballLeagueOne.setName("Somewhership");
        footballLeagueOne.setCountry(countryRepository.findOneByName("Somewheria"));

        FootballLeague footballLeagueTwo = new FootballLeague();
        footballLeagueTwo.setName("Randomship");
        footballLeagueTwo.setCountry(countryRepository.findOneByName("Randomia"));

        List<FootballLeague> list = new ArrayList<>();
        list.add(footballLeagueOne);
        list.add(footballLeagueTwo);

        footballLeagueRepository.saveAll(list);
    }

    public List<FootballLeague> allLeagues(){
        return footballLeagueRepository.findAll();
    }

    public List<FootballLeague> findLeagueByCountryId(Long countryID){
        return footballLeagueRepository.findAllByCountry_Id(countryID);
    }

    public FootballLeague findLeagueById(Long leagueID){
        Optional<FootballLeague> league = footballLeagueRepository.findById(leagueID);
        FootballLeague footballLeague1 = new FootballLeague();
        if (league.isPresent()) {
            footballLeague1 = league.get();
        }
        return footballLeague1;
    }
}
