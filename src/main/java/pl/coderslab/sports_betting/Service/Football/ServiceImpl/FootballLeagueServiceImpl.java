package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Football.FootballLeague;
import pl.coderslab.sports_betting.Repository.General.CountryRepository;
import pl.coderslab.sports_betting.Repository.Fotball.FootballLeagueRepository;
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

    /**
     * Method populate database with leagues and save them to
     * database
     */
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

    /**
     * Method is looking for all football leagues
     * @return list of all football leagues
     */
    public List<FootballLeague> allLeagues(){
        return footballLeagueRepository.findAll();
    }

    /**
     * Searches for football leagues by country Id
     * @param countryID Id of selected country
     * @return list of all leagues in selected country
     */
    public List<FootballLeague> findLeagueByCountryId(Long countryID){
        return footballLeagueRepository.findAllByCountry_Id(countryID);
    }

    /**
     * Looking for single football league by league id
     * @param leagueID league id
     * @return single football league
     */
    public FootballLeague findLeagueById(Long leagueID){
        FootballLeague league = footballLeagueRepository.getOne(leagueID);
        return league;
    }
}
