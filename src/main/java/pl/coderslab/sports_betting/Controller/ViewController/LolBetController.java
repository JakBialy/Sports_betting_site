package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.Lol.Service.LolBetService;
import pl.coderslab.sports_betting.Service.Lol.Service.LolMatchService;
import pl.coderslab.sports_betting.Service.Security.Service.UserService;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("/lolBet")
public class LolBetController {

    @Autowired
    LolMatchService lolMatchService;

    @Autowired
    LolBetService lolBetService;

    @Autowired
    UserService userService;

    @GetMapping("/match/{id}")
    public String betForm(@PathVariable Long id, Model model) {
        model.addAttribute("matchData", lolMatchService.findById(id));
        model.addAttribute("bet", new LolBet());
        model.addAttribute("user", userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "Lol/LolBetForm";
    }

    @PostMapping("/match")
    public String postForm(@Valid @ModelAttribute LolBet lolBet, BindingResult result) {
        if(result.hasErrors()){
            return "redirect:/lolBet/match/" + lolBet.getLolMatch().getId();
        }
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        BigDecimal balance = user.getMoney().subtract(lolBet.getMoney());

        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            return "redirect:/user/noFounds";
        } else {
            lolBetService.saveBet(lolBet);
            return "redirect:/lol";
        }
    }
}
