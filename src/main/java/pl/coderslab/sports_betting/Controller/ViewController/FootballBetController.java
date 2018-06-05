package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.Football.FootballMatchService;
import pl.coderslab.sports_betting.Service.Football.FootballBetService;
import pl.coderslab.sports_betting.Service.Security.UserService;


import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/bet")
public class FootballBetController {

    @Autowired
    FootballMatchService footballMatchService;

    @Autowired
    FootballBetService footballBetService;

    @Autowired
    UserService userService;

    @GetMapping("/match/{id}")
    public String betForm(@PathVariable Long id, Model model) {
        model.addAttribute("matchData", footballMatchService.findById(id));
        model.addAttribute("bet", new FootballBet());
        model.addAttribute("user", userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "Bets/BetForm";
    }

    @PostMapping("/match")
    public String postForm(@Valid @ModelAttribute FootballBet footballBet, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/footballBet/match/" + footballBet.getFootballMatch().getId();
        }
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        BigDecimal balance = user.getMoney().subtract(footballBet.getMoney());

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            return "redirect:/user/noFounds";
        } else {
            footballBetService.saveBet(footballBet);
            return "redirect:/football";
        }
    }
}
