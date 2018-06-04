package pl.coderslab.sports_betting.Controller.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sports_betting.Entity.Country;
import pl.coderslab.sports_betting.Service.CountryService;
import pl.coderslab.sports_betting.Service.LeagueService;
import pl.coderslab.sports_betting.Service.Security.UserService;
import pl.coderslab.sports_betting.Service.TeamService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CountryService countryService;

    @Autowired
    LeagueService leagueService;

    @Autowired
    TeamService teamService;

    @Autowired
    UserService userService;

    @GetMapping("")
    public String adminIndex() {
        return "AdminIndex";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users",  userService.findAll());
        return "AdminUserList";
    }
}
