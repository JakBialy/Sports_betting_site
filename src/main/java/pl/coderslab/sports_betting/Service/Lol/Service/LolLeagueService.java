package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolLeague;

import java.util.List;

public interface LolLeagueService {

    public void populateDb();

    public List<LolLeague> allLeagues();


    public LolLeague findLeagueById(Long leagueID);

    }
