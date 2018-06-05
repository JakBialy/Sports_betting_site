package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.Message;
import pl.coderslab.sports_betting.Entity.Transaction;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.BetService;
import pl.coderslab.sports_betting.Service.MessageService;
import pl.coderslab.sports_betting.Service.TransactionService;
import pl.coderslab.sports_betting.Service.Security.UserService;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    @Autowired
    MessageService messageService;

    @GetMapping("/bets")
    public String userBets(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Bet> list = betService.findAllByUser(user);
        model.addAttribute("bets", list);
        return "User/UserBetList";
    }

    @GetMapping("/Founds")
    public String addFounds(Model model, HttpSession httpSession) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
            return "User/UserTransactionIndex";
    }

    @GetMapping("/noFounds")
    public String addFounds(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        model.addAttribute("money", 0);
        return "User/UserTransactionIndex";
    }

    @GetMapping("/creditAdd")
    public String addFoundsFromCard(Model model) {
        model.addAttribute("transaction", new Transaction());
        return "User/UserTransactionCardAdd";
    }

    @PostMapping("/creditAdd")
    public String addFoundsFromCard(@Valid @ModelAttribute Transaction transaction, BindingResult result) {
        if (result.hasErrors()) {
            return "User/UserTransactionCardAdd";
        }
        transactionService.saveTransaction(transaction);
        return "redirect:/user/userInfo";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "User/UserInfo";
    }

    @GetMapping("/transactionHistory/{id}")
    public String transactionHistory(@PathVariable Long id, Model model) {
        List<Transaction> list = transactionService.findAllByUserId(id);
        model.addAttribute("transactions", list);
        return "User/UserTransactionsList";
    }

    @GetMapping("/userSettings")
    public String userSettings(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setPassword("");
        model.addAttribute("user", user);
        return "User/UserSettings";
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
        return "User/UserFavorites";
    }

    @GetMapping("/favoriteRemove/{id}")
    public String removeFavorite(@PathVariable Long id) {
        userService.removeFromFavorite(id);
        return "redirect:/user/favorites";
    }

    @GetMapping("/all")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.findAllWithoutActiveUser());
        return "User/UserListFriends";
    }

    @GetMapping("/addFriend/{id}")
    public String addToFriend(@PathVariable Long id) {
        userService.addToFriends(id);
        return "redirect:/user/friends";
    }

    @GetMapping("/friends")
    public String addToFriend(Model model) {
        model.addAttribute("friends",userService.getAllUserFriends());
        return "User/UserFriends";
    }

    @GetMapping("/addMessage")
    public String messageForm(Model model) {
        model.addAttribute("message", new Message());
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "User/UserMessageForm";
    }

    @PostMapping("/addMessage")
    public String messageFormSave(@Valid @ModelAttribute Message message, BindingResult result){
        if(result.hasErrors()){
            return "User/UserMessageForm";
        }
        messageService.saveMessage(message);
        return "redirect:/user/messages";
    }

    @GetMapping("/messages")
    public String showUserMessages(Model model) {
        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("received", messageService.getAllReceivedMessages(user.getId()));
        model.addAttribute("send", messageService.getAllSendMessages(user.getId()));
        return "User/UserMessagesList";
    }
}
