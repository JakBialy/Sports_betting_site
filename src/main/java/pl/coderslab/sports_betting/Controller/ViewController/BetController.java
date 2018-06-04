package pl.coderslab.sports_betting.Controller.ViewController;

import com.sun.deploy.net.HttpResponse;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.MatchService;
import pl.coderslab.sports_betting.Service.BetService;
import pl.coderslab.sports_betting.Service.Security.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;

@Controller
@RequestMapping("/bet")
public class BetController {

    @Autowired
    MatchService matchService;

    @Autowired
    BetService betService;

    @Autowired
    UserService userService;

    @GetMapping("/match/{id}")
    public String betForm(@PathVariable Long id, Model model) {
        model.addAttribute("matchData", matchService.findById(id));
        model.addAttribute("bet", new Bet());
        return "BetForm";
    }

    @PostMapping("/form")
    public String postForm(@Valid @ModelAttribute Bet bet, BindingResult result, HttpSession httpSession) {
        if(result.hasErrors()){
            return "BetForm";
        }
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        BigDecimal balance = user.getMoney().subtract(bet.getMoney());
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            return "redirect:/user/noFounds";
        }
        else {
            betService.saveBet(bet);
            return "redirect:/football";
        }
    }
}
