package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballTeam;

import java.util.List;

public interface FootballTeamService {

    public void populateDb();

    public List<FootballTeam> allTeams();

    public List<FootballTeam> findTeamsByLeagueId(Long leagueId);

    public FootballTeam findTeamById(Long teamID);

    }
