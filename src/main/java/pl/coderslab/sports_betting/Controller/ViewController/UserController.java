package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.Transaction;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.BetService;
import pl.coderslab.sports_betting.Service.TransactionService;
import pl.coderslab.sports_betting.Service.Security.UserService;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    BetService betService;
    @Autowired
    TransactionService transactionService;

    @GetMapping("/bets")
    public String userBets(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Bet> list = betService.findAllByUser(user);
        model.addAttribute("bets", list);
        return "BetList";
    }

    @GetMapping("/Founds")
    public String addFounds(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "TransactionIndex";
    }

    @GetMapping("/creditAdd")
    public String addFoundsFromCard(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "TransactionCardAdd";
    }

    @PostMapping("/creditAdd")
    public String addFoundsFromCard(@Valid @ModelAttribute Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            return "TransactionCardAdd";
        }
        transactionService.saveTransaction(transaction);
        return "redirect:/football";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "UserInfo";
    }

    @GetMapping("/transactionHistory/{id}")
    public String transactionHistory(@PathVariable Long id, Model model) {
        List<Transaction> list = transactionService.findAllByUserId(id);
        model.addAttribute("transactions", list);
        return "TransactionsList";
    }

    @GetMapping("/userSettings")
    public String userSettings(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setPassword("");
        model.addAttribute("user", user);
        return "UserSettings";
    }

    @PostMapping("/userSettings")
    public String userSettings(@RequestParam String username, String firstName, String lastName, String nick, String password) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setNick(nick);
        user.setPassword(password);
        userService.editUser(user);
        return "redirect:/user/userInfo";
    }
}

