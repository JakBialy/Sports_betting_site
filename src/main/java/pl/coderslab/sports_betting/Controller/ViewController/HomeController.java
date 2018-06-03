package pl.coderslab.sports_betting.Controller.ViewController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.UserRepository;
import pl.coderslab.sports_betting.Service.Security.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String index(HttpSession httpSession) {
        userService.userDetailsToSession(httpSession);
        return "Index";
    }
}
