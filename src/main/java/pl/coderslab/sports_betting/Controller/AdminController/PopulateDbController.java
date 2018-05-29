package pl.coderslab.sports_betting.Controller.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.sports_betting.Service.CountryService;
import pl.coderslab.sports_betting.Service.LeagueService;
import pl.coderslab.sports_betting.Service.TeamService;

@Controller
@RequestMapping("/admin")
public class PopulateDbController {

    @Autowired
    CountryService countryService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    TeamService teamService;

    @GetMapping(path = "/countries")
    @ResponseBody
    public String countriesInDb() {
        countryService.populateDb();
        return "Countries are in DB now! :)";
    }

    @GetMapping(path = "/leagues")
    @ResponseBody
    public String leaguesInDb() {
        leagueService.populateDb();
        return "Leagues are in DB now! :)";
    }

    @GetMapping(path = "/teams")
    @ResponseBody
    public String teamsInDb() {
        teamService.populateDb();
        return "Teams are in DB now! :)";
    }
}
