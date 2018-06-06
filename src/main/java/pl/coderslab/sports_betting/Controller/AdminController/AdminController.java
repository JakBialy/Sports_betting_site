package pl.coderslab.sports_betting.Controller.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.Football.Service.CountryService;
import pl.coderslab.sports_betting.Service.Football.Service.FootballBetService;
import pl.coderslab.sports_betting.Service.Football.Service.FootballLeagueService;
import pl.coderslab.sports_betting.Service.Football.Service.FootballTeamService;

import pl.coderslab.sports_betting.Service.Lol.Service.LolBetService;
import pl.coderslab.sports_betting.Service.Security.Service.UserService;

import javax.validation.Valid;
import java.util.List;

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
    @Autowired
    LolBetService lolBetService;
    @Autowired
    FootballBetService footballBetService;

    @GetMapping("")
    public String adminIndex() {
        return "Admin/AdminIndex";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users",  userService.findAll());
        return "Admin/AdminUserList";
    }

    @GetMapping("/userDelete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/userBets/{id}")
    public String userBets(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        List<FootballBet> footballBetList = footballBetService.findAllByUser(user);
        List<LolBet> lolBetList = lolBetService.findAllByUser(user);
        model.addAttribute("footballBets", footballBetList);
        model.addAttribute("lolBets", lolBetList);
        return "User/UserBetList";
    }

    @GetMapping("/createAdmin")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "Admin/AdminRegister";
    }

    @PostMapping("/createAdmin")
    public String userFormSave(@Valid @ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
            return "Admin/AdminRegister";
        }
        userService.saveAdmin(user);
        return "redirect:/admin/users";
    }
}
