package pl.coderslab.sports_betting.Controller.RestApiController.Lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Lol.LolMatch;
import pl.coderslab.sports_betting.Service.Lol.Service.LolMatchService;

import java.util.List;

@RestController
@RequestMapping("/api/lolMatch")
public class LoLMatchAPIController {

    private final
    LolMatchService lolMatchService;

    @Autowired
    public LoLMatchAPIController(LolMatchService lolMatchService) {
        this.lolMatchService = lolMatchService;
    }

    @GetMapping(path = "/all")
    public List<LolMatch> getAllMatches() {
        return	lolMatchService.allMatches();
    }

    @GetMapping(path = "/live")
    public List<LolMatch> getAllLive() {
        return	lolMatchService.liveMatches();
    }

    @GetMapping(path = "/home/{id}")
    public List<LolMatch> getAllHomeByTeam (@PathVariable Long id) {
        return	lolMatchService.homeMatches(id);
    }

    @GetMapping(path = "/away/{id}")
    public List<LolMatch> getAllAwayByTeam(@PathVariable Long id) {
        return	lolMatchService.awayMatches(id);
    }

    @GetMapping(path = "/betweenDate/{date1}/{date2}")
    public List<LolMatch> getAllAwayByStartBetween(@PathVariable String date1, @PathVariable String date2) {
        return	lolMatchService.findMatchesWhereStartIsBetween(date1, date2);
    }
}