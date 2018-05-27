package pl.coderslab.sports_betting.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/football")
public class FootballController {

    @GetMapping("")
    public String homeFootball() {
        return "Football";
    }
}
