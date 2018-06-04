package pl.coderslab.sports_betting.Controller.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Country;
import pl.coderslab.sports_betting.Entity.League;
import pl.coderslab.sports_betting.Service.LeagueService;

import java.util.List;

@RestController
@RequestMapping("/api/league")
public class LeagueAPIController {

    @Autowired
    LeagueService leagueService;

    @GetMapping(path = "/all")
    public List<League> getAllLeagues() {
        return	leagueService.allLeagues();
    }

    @GetMapping(path = "/country/{id}")
    public List<League> getAllLeagues(Long id) {
        return	leagueService.findLeagueByCountryId(id);
    }

    // leagues 5,6
    @GetMapping(path = "/{id}")
    public League getLeagueById(@PathVariable Long id) {
        return leagueService.findLeagueById(id);
    }
}
