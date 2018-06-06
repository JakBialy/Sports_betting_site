package pl.coderslab.sports_betting.Service.Lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Lol.LolLeague;
import pl.coderslab.sports_betting.Repository.Lol.LolLeagueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class LolLeagueService {
    @Autowired
    LolLeagueRepository lolLeagueRepository;

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

    public List<LolLeague> allLeagues(){
        return lolLeagueRepository.findAll();
    }

    public LolLeague findLeagueById(Long leagueID){
        Optional<LolLeague> league = lolLeagueRepository.findById(leagueID);
        LolLeague lolLeague1 = new LolLeague();
        if (league.isPresent()) {
            lolLeague1 = league.get();
        }
        return lolLeague1;
    }
}
