package pl.coderslab.sports_betting.Controller.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.League;
import pl.coderslab.sports_betting.Service.LeagueService;

import java.util.List;

@RestController
@RequestMapping("/api/league")
public class LeagueAPIController {

    @Autowired
    LeagueService leagueService;

    @RequestMapping(path = "/all")
    public List<League> sample() {
        return	leagueService.allLeagues();
    }

    // should be sth like in API Footbal, exact id = exact country, match, league etc
}
