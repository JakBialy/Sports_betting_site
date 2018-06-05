package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.FootballMatchService;
import pl.coderslab.sports_betting.Service.BetService;
import pl.coderslab.sports_betting.Service.Security.UserService;


import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/bet")
public class BetController {

    @Autowired
    FootballMatchService footballMatchService;

    @Autowired
    BetService betService;

    @Autowired
    UserService userService;

    @GetMapping("/match/{id}")
    public String betForm(@PathVariable Long id, Model model) {
        model.addAttribute("matchData", footballMatchService.findById(id));
        model.addAttribute("bet", new Bet());
        model.addAttribute("user", userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "Bets/BetForm";
    }

    @PostMapping("/match")
    public String postForm(@Valid @ModelAttribute Bet bet, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/bet/match/" + bet.getFootballMatch().getId();
        }
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        BigDecimal balance = user.getMoney().subtract(bet.getMoney());

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            return "redirect:/user/noFounds";
        } else {
            betService.saveBet(bet);
            return "redirect:/football";
        }
    }
}
