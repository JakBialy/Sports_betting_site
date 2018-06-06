package pl.coderslab.sports_betting.Controller.RestApiController.Lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Lol.LolLeague;
import pl.coderslab.sports_betting.Service.Lol.Service.LolLeagueService;

import java.util.List;

@RestController
@RequestMapping("/api/lolLeague")
public class LoLLeagueAPIController {

    @Autowired
    LolLeagueService lolLeagueService;

    @GetMapping(path = "/all")
    public List<LolLeague> getAllLeagues() {
        return	lolLeagueService.allLeagues();
    }

    @GetMapping(path = "/{id}")
    public LolLeague getLeagueById(@PathVariable Long id) {
        return lolLeagueService.findLeagueById(id);
    }
}
