package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.General.Service.KeyApiService;
import pl.coderslab.sports_betting.Service.Security.Service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    private final
    UserService userService;
    private final
    KeyApiService keyApiService;

    @Autowired
    public HomeController(UserService userService, KeyApiService keyApiService) {
        this.userService = userService;
        this.keyApiService = keyApiService;
    }

    @GetMapping("/index")
    public String index(Model model,HttpSession httpSession) {
        userService.userDetailsToSession(httpSession);
        model.addAttribute("favorite",userService.checkFavorite());
        return "Index";
    }

    @GetMapping("/checkApi")
    public String api() {
        return "API";
    }

    @GetMapping("/checkApi/generateKey")
    public String generateApiKey(Model model) {
        model.addAttribute("key", keyApiService.giveRandomKey());
        return "ShowKey";
    }

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
}
