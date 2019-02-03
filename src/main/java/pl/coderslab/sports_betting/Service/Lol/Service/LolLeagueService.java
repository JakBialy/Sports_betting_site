package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolLeague;

import java.util.List;

public interface LolLeagueService {
     void populateDb();
     List<LolLeague> allLeagues();
     LolLeague findLeagueById(Long leagueID);
}
