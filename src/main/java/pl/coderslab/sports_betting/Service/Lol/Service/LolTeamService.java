package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolTeam;

import java.util.List;

public interface LolTeamService {
     void populateDb();
     List<LolTeam> allTeams();
     List<LolTeam> findTeamsByLeagueId(Long leagueId);
     LolTeam findTeamById(Long teamID);
}
