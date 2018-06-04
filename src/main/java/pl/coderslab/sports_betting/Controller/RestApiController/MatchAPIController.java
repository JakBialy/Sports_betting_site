package pl.coderslab.sports_betting.Controller.RestApiController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.coderslab.sports_betting.Entity.Match;
import pl.coderslab.sports_betting.Entity.Team;
import pl.coderslab.sports_betting.Service.MatchService;
import pl.coderslab.sports_betting.Service.TeamService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchAPIController {

    @Autowired
    MatchService matchService;

    @GetMapping(path = "/all")
    public List<Match> getAllMatches() {
        return	matchService.allMatches();
    }

    @GetMapping(path = "/live")
    public List<Match> getAllLive() {
        return	matchService.liveMatches();
    }

    @GetMapping(path = "/home/{id}")
    public List<Match> getAllHomeByTeam (@PathVariable Long id) {
        return	matchService.homeMatches(id);
    }

    @GetMapping(path = "/away/{id}")
    public List<Match> getAllAwayByTeam(@PathVariable Long id) {
        return	matchService.awayMatches(id);
    }

    @GetMapping(path = "/betweenDate/{date1}/{date2}")
    public List<Match> getAllAwayByStartBetween(@PathVariable String date1,@PathVariable String date2) {
        return	matchService.findMatchesWhereStartIsBetween(date1, date2);
    }

    // should be sth like in API Footbal, exact id = exact country, match, league etc
}