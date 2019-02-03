package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballLeague;

import java.util.List;

public interface FootballLeagueService {
     void populateDb();
     List<FootballLeague> allLeagues();
     List<FootballLeague> findLeagueByCountryId(Long countryID);
     FootballLeague findLeagueById(Long leagueID);

    }
