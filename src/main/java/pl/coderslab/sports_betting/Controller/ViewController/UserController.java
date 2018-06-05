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


import javax.servlet.http.HttpSession;
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
    public String addFounds(Model model, HttpSession httpSession) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
            return "TransactionIndex";
    }

    @GetMapping("/noFounds")
    public String addFounds(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        model.addAttribute("money", 0);
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
        userService.editUser(username,firstName,lastName,nick,password);
        return "redirect:/user/userInfo";
    }

    @GetMapping("/addFavorite/{id}")
    public String addFavorite(@PathVariable Long id) {
        userService.addToFavorites(id);
        return "redirect:/user/favorites";
    }

    @GetMapping("/favorites")
    public String showFavorites(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "UserFavorites";
    }

    @GetMapping("/favoriteRemove/{id}")
    public String removeFavorite(@PathVariable Long id) {
        userService.removeFromFavorite(id);
        return "redirect:/user/favorites";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAllWithoutActiveUser());
        return "UserListFriends";
    }

    @GetMapping("/addFriend/{id}")
    public String addToFriend(@PathVariable Long id) {
        userService.addToFriends(id);
        return "redirect:/user/friends";
    }

    @GetMapping("/friends")
    public String addToFriend(Model model) {
        model.addAttribute("friends",userService.getAllUserFriends());
        return "UserFriends";
    }
}