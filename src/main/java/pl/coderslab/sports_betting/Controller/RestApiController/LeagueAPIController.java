package pl.coderslab.sports_betting.Controller.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.FootballLeague;
import pl.coderslab.sports_betting.Service.FootballLeagueService;

import java.util.List;

@RestController
@RequestMapping("/api/league")
public class LeagueAPIController {

    @Autowired
    FootballLeagueService footballLeagueService;

    @GetMapping(path = "/all")
    public List<FootballLeague> getAllLeagues() {
        return	footballLeagueService.allLeagues();
    }

    @GetMapping(path = "/country/{id}")
    public List<FootballLeague> getAllLeagues(Long id) {
        return	footballLeagueService.findLeagueByCountryId(id);
    }

    // footballLeagues 5,6
    @GetMapping(path = "/{id}")
    public FootballLeague getLeagueById(@PathVariable Long id) {
        return footballLeagueService.findLeagueById(id);
    }
}
