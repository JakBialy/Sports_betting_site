package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.BetService;
import pl.coderslab.sports_betting.Service.Security.UserService;


import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    BetService betService;

    @GetMapping("/register")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "Register";
    }

    @PostMapping("/register")
    public String userFormSave(@Valid @ModelAttribute User user, BindingResult result){
        if(result.hasErrors()){
            return "Register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/user/bets")
    public String userBets(Model model){
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Bet> list = betService.findAllByUser(user);
        model.addAttribute("bets", list);
        return "BetList";
    }
}
