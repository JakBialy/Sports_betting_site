package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.KeyApiService;
import pl.coderslab.sports_betting.Service.Security.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    UserService userService;
    @Autowired
    KeyApiService keyApiService;

    @GetMapping("/index")
    public String index(HttpSession httpSession) {
        userService.userDetailsToSession(httpSession);
        return "Index";
    }

    @GetMapping("/api")
    public String api() {
        return "API";
    }

    @GetMapping("/api/generateKey")
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
