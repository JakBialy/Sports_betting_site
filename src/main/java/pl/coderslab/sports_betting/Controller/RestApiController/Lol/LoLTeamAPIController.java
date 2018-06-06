package pl.coderslab.sports_betting.Controller.RestApiController.Lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Lol.LolTeam;
import pl.coderslab.sports_betting.Service.Lol.Service.LolTeamService;

import java.util.List;

@RestController
@RequestMapping("/api/lolTeam")
public class LoLTeamAPIController {

    @Autowired
    LolTeamService lolTeamService;

    @GetMapping(path = "/all")
    public List<LolTeam> getAllTeams() {
        return	lolTeamService.allTeams();
    }

    @GetMapping(path = "/league/{id}")
    public List<LolTeam> getTeamsByLeagueId(@PathVariable Long id) {
        return	lolTeamService.findTeamsByLeagueId(id);
    }

    @GetMapping(path = "/{id}")
    public LolTeam getTeamById(@PathVariable Long id) {
        return	lolTeamService.findTeamById(id);
    }

}