package pl.coderslab.sports_betting.Service.Lol.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Lol.LolLeague;
import pl.coderslab.sports_betting.Repository.Lol.LolLeagueRepository;
import pl.coderslab.sports_betting.Service.Lol.Service.LolLeagueService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LolLeagueServiceImpl implements LolLeagueService {
    @Autowired
    LolLeagueRepository lolLeagueRepository;

    /**
     * Method populate database with leagues and save them to
     * database
     */
    public void populateDb() {

        LolLeague lolLeagueOne = new LolLeague();
        lolLeagueOne.setName("EU West");

        LolLeague lolLeagueTwo = new LolLeague();
        lolLeagueTwo.setName("EU East & Nordic");

        List<LolLeague> list = new ArrayList<>();
        list.add(lolLeagueOne);
        list.add(lolLeagueTwo);

        lolLeagueRepository.saveAll(list);
    }

    /**
     * Method is looking for all lol leagues
     * @return list of all lol leagues
     */
    public List<LolLeague> allLeagues(){
        return lolLeagueRepository.findAll();
    }

    /**
     * Looking for single lol league by league id
     * @param leagueID league id
     * @return single lol league
     */
    public LolLeague findLeagueById(Long leagueID){
        Optional<LolLeague> league = lolLeagueRepository.findById(leagueID);
        LolLeague lolLeague1 = new LolLeague();
        if (league.isPresent()) {
            lolLeague1 = league.get();
        }
        return lolLeague1;
    }
}
