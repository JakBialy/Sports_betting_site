package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.MatchService;
import pl.coderslab.sports_betting.Service.Security.BetService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/bet")
public class BetController {

    @Autowired
    MatchService matchService;

    @Autowired
    BetService betService;

    @GetMapping("/match/{id}")
    public String betForm(@PathVariable Long id, Model model) {
        model.addAttribute("matchData", matchService.findById(id));
        model.addAttribute("bet", new Bet());
        return "BetForm";
    }

    @PostMapping("/form")
    public String postForm(@Valid @ModelAttribute Bet bet, BindingResult result) {
        if(result.hasErrors()){
            return "BetForm";
        }
        betService.saveBet(bet);
        return "redirect:/football";
    }
}
