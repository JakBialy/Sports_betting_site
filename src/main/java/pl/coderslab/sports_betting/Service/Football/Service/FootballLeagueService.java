package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballLeague;

import java.util.List;

public interface FootballLeagueService {

    public void populateDb();

    public List<FootballLeague> allLeagues();

    public List<FootballLeague> findLeagueByCountryId(Long countryID);

    public FootballLeague findLeagueById(Long leagueID);

    }
