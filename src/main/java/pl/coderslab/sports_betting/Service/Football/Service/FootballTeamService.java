package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballTeam;

import java.util.List;

public interface FootballTeamService {
     void populateDb();
     List<FootballTeam> allTeams();
     List<FootballTeam> findTeamsByLeagueId(Long leagueId);
     FootballTeam findTeamById(Long teamID);
}
