package pl.coderslab.sports_betting.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.Security.UserService;


import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserService userService;

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
