package pl.coderslab.sports_betting.Controller.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.FootballMatch;
import pl.coderslab.sports_betting.Service.FootballMatchService;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchAPIController {

    @Autowired
    FootballMatchService footballMatchService;

    @GetMapping(path = "/all")
    public List<FootballMatch> getAllMatches() {
        return	footballMatchService.allMatches();
    }

    @GetMapping(path = "/live")
    public List<FootballMatch> getAllLive() {
        return	footballMatchService.liveMatches();
    }

    @GetMapping(path = "/home/{id}")
    public List<FootballMatch> getAllHomeByTeam (@PathVariable Long id) {
        return	footballMatchService.homeMatches(id);
    }

    @GetMapping(path = "/away/{id}")
    public List<FootballMatch> getAllAwayByTeam(@PathVariable Long id) {
        return	footballMatchService.awayMatches(id);
    }

    @GetMapping(path = "/betweenDate/{date1}/{date2}")
    public List<FootballMatch> getAllAwayByStartBetween(@PathVariable String date1, @PathVariable String date2) {
        return	footballMatchService.findMatchesWhereStartIsBetween(date1, date2);
    }

    // should be sth like in API Footbal, exact id = exact country, footballMatch, footballLeague etc
}