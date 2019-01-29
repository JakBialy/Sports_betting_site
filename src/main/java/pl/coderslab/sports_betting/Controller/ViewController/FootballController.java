package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sports_betting.Service.Football.Service.CountryService;
import pl.coderslab.sports_betting.Service.Football.Service.FootballLeagueService;
import pl.coderslab.sports_betting.Service.Football.Service.FootballMatchService;
import pl.coderslab.sports_betting.Service.Football.Service.FootballTeamService;


@Controller
@RequestMapping(value = "/football")
public class FootballController {

    private final
    FootballMatchService footballMatchService;
    private final
    CountryService countryService;
    private final
    FootballLeagueService footballLeagueService;
    private final
    FootballTeamService footballTeamService;

    @Autowired
    public FootballController(FootballMatchService footballMatchService, CountryService countryService, FootballLeagueService footballLeagueService, FootballTeamService footballTeamService) {
        this.footballMatchService = footballMatchService;
        this.countryService = countryService;
        this.footballLeagueService = footballLeagueService;
        this.footballTeamService = footballTeamService;
    }


    @GetMapping("")
    public String homeFootball(Model model) {
        model.addAttribute("countries", countryService.allCountries());
        model.addAttribute("nextMatches", footballMatchService.plannedMatches());
        model.addAttribute("liveMatches", footballMatchService.liveMatches());
        return "Football/Football";
    }

    @GetMapping("/country/{id}")
    public String countriesLeagues(@PathVariable Long id, Model model) {
        model.addAttribute("leagues", footballLeagueService.findLeagueByCountryId(id));
        model.addAttribute("country", countryService.getCountryById(id));
        return "Football/FootballLeagues";
    }

    @GetMapping("/league/{id}")
    public String leagueTeams(@PathVariable Long id, Model model) {
        model.addAttribute("league", footballLeagueService.findLeagueById(id));
        model.addAttribute("teams", footballTeamService.findTeamsByLeagueId(id));
        return "Football/FootballTeams";
    }

    @GetMapping("/team/{id}")
    public String teamMatches(@PathVariable Long id, Model model) {
        model.addAttribute("team", footballTeamService.findTeamById(id));
        model.addAttribute("homeMatches", footballMatchService.homeMatches(id));
        model.addAttribute("awayMatches", footballMatchService.awayMatches(id));
        return "Football/TeamMatchesList";
    }

    @GetMapping("/match/{id}")
    public String singleMatch(@PathVariable Long id, Model model){
        model.addAttribute("match", footballMatchService.findById(id));
        return "Football/FootballMatch";
    }
}

