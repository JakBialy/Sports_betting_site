package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolTeam;

import java.util.List;

public interface LolTeamService {

    public void populateDb();

    public List<LolTeam> allTeams();

    public List<LolTeam> findTeamsByLeagueId(Long leagueId);

    public LolTeam findTeamById(Long teamID);

    }
