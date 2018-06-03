package pl.coderslab.sports_betting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.Country;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.CountryService;
import pl.coderslab.sports_betting.Service.LeagueService;
import pl.coderslab.sports_betting.Service.Security.UserService;
import pl.coderslab.sports_betting.Service.TeamService;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppStartup implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    CountryService countryService;
    @Autowired
    LeagueService leagueService;
    @Autowired
    TeamService teamService;

    @Autowired
    public AppStartup(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userService.getNumUsers() == 0L) {
            userService.saveUser(testUser());
        }
        countryService.populateDb();
        leagueService.populateDb();
        teamService.populateDb();
    }

    private User testUser() {
        User user = new User();
        user.setUsername("bialyj1@gmail.com");
        user.setPassword("123");
        user.setNick("bialy93");
        user.setFirstName("Jakub");
        user.setLastName("Bialy");
        user.setEnabled(true);
        return user;
    }
}
