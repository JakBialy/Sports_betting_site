package pl.coderslab.sports_betting.Controller.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Football.FootballTeam;
import pl.coderslab.sports_betting.Service.Football.FootballTeamService;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamAPIController {

    @Autowired
    FootballTeamService footballTeamService;

    @GetMapping(path = "/all")
    public List<FootballTeam> getAllTeams() {
        return	footballTeamService.allTeams();
    }

    @GetMapping(path = "/league/{id}")
    public List<FootballTeam> getTeamsByLeagueId(@PathVariable Long id) {
        return	footballTeamService.findTeamsByLeagueId(id);
    }

    @GetMapping(path = "/{id}")
    public FootballTeam getTeamById(@PathVariable Long id) {
        return	footballTeamService.findTeamById(id);
    }

    // should be sth like in API Footbal, exact id = exact country, footballMatch, footballLeague etc
}