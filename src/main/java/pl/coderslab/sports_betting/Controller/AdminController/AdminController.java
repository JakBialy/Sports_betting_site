package pl.coderslab.sports_betting.Controller.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.sports_betting.Service.CountryService;
import pl.coderslab.sports_betting.Service.Football.FootballLeagueService;
import pl.coderslab.sports_betting.Service.Security.UserService;
import pl.coderslab.sports_betting.Service.Football.FootballTeamService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CountryService countryService;

    @Autowired
    FootballLeagueService footballLeagueService;

    @Autowired
    FootballTeamService footballTeamService;

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

    @GetMapping("/userDelete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
