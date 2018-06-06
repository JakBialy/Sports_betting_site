package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sports_betting.Service.Football.Service.CountryService;
import pl.coderslab.sports_betting.Service.Lol.Service.LolLeagueService;
import pl.coderslab.sports_betting.Service.Lol.Service.LolMatchService;
import pl.coderslab.sports_betting.Service.Lol.Service.LolTeamService;


@Controller
@RequestMapping(value = "/lol")
public class LolController {

    @Autowired
    LolMatchService lolMatchService;
    @Autowired
    CountryService countryService;
    @Autowired
    LolLeagueService lolLeagueService;
    @Autowired
    LolTeamService lolTeamService;


    @GetMapping("")
    public String homeLol(Model model) {

        model.addAttribute("leagues", lolLeagueService.allLeagues());
        model.addAttribute("nextMatches", lolMatchService.plannedMatches());
        model.addAttribute("liveMatches", lolMatchService.liveMatches());
        return "Lol/Lol";
    }

    @GetMapping("/league/{id}")
    public String leagueTeams(@PathVariable Long id, Model model) {
        model.addAttribute("league", lolLeagueService.findLeagueById(id));
        model.addAttribute("teams", lolTeamService.findTeamsByLeagueId(id));
        return "Lol/LolTeams";
    }

    @GetMapping("/team/{id}")
    public String teamMatches(@PathVariable Long id, Model model) {
        model.addAttribute("team", lolTeamService.findTeamById(id));
        model.addAttribute("homeMatches", lolMatchService.homeMatches(id));
        model.addAttribute("awayMatches", lolMatchService.awayMatches(id));
        return "Lol/LolTeamMatchesList";
    }

    @GetMapping("/match/{id}")
    public String singleMatch(@PathVariable Long id, Model model){
        model.addAttribute("match", lolMatchService.findById(id));
        return "Lol/LolMatch";
    }
}

