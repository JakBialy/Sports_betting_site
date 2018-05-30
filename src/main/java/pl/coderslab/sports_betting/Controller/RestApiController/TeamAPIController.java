package pl.coderslab.sports_betting.Controller.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Team;
import pl.coderslab.sports_betting.Service.TeamService;

import java.util.List;

@RestController
@RequestMapping("/api/team")
public class TeamAPIController {

    @Autowired
    TeamService teamService;

    @RequestMapping(path = "/all")
    public List<Team> sample() {
        return	teamService.allTeams();
    }

    // should be sth like in API Footbal, exact id = exact country, match, league etc
}