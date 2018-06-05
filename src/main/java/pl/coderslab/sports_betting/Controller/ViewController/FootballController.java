package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sports_betting.Service.CountryService;
import pl.coderslab.sports_betting.Service.LeagueService;
import pl.coderslab.sports_betting.Service.MatchService;
import pl.coderslab.sports_betting.Service.TeamService;

@Controller
@RequestMapping(value = "/football")
public class FootballController {

    @Autowired
    MatchService matchService;
    @Autowired
    CountryService countryService;
    @Autowired
    LeagueService leagueService;
    @Autowired
    TeamService teamService;


    @GetMapping("")
    public String homeFootball(Model model) {

        model.addAttribute("countries", countryService.allCountries());
        model.addAttribute("nextMatches", matchService.plannedMatches());
        model.addAttribute("liveMatches", matchService.liveMatches());

        return "Football/Football";
    }

    @GetMapping("/country/{id}")
    public String countriesLeagues(@PathVariable Long id, Model model) {
        model.addAttribute("leagues", leagueService.findLeagueByCountryId(id));
        model.addAttribute("country", countryService.getCountryById(id));
        return "Football/FootballLeagues";
    }

    @GetMapping("/league/{id}")
    public String leagueTeams(@PathVariable Long id, Model model) {
        model.addAttribute("league", leagueService.findLeagueById(id));
        model.addAttribute("teams", teamService.findTeamsByLeagueId(id));
        return "Football/FootballTeams";
    }

    @GetMapping("/team/{id}")
    public String teamMatches(@PathVariable Long id, Model model) {
        model.addAttribute("team", teamService.findTeamById(id));
        model.addAttribute("homeMatches", matchService.homeMatches(id));
        model.addAttribute("awayMatches", matchService.awayMatches(id));
        return "Football/TeamMatchesList";
    }

    @GetMapping("/match/{id}")
    public String singleMatch(@PathVariable Long id, Model model){
        model.addAttribute("match", matchService.findById(id));
        return "Football/FotballMatch";
    }
}
