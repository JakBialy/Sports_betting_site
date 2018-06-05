package pl.coderslab.sports_betting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.CountryService;
import pl.coderslab.sports_betting.Service.Football.FootballLeagueService;
import pl.coderslab.sports_betting.Service.Security.UserService;
import pl.coderslab.sports_betting.Service.Football.FootballTeamService;

@Component
public class AppStartup implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    CountryService countryService;
    @Autowired
    FootballLeagueService footballLeagueService;
    @Autowired
    FootballTeamService footballTeamService;

    @Autowired
    public AppStartup(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userService.getNumUsers() == 0L) {
            userService.saveUser(testUser1());
            userService.saveUser(testUser2());
            userService.saveAdmin(testAdmin1());

        }
        countryService.populateDb();
        footballLeagueService.populateDb();
        footballTeamService.populateDb();
    }

    private User testUser1() {
        User user = new User();
        user.setUsername("bialyj1@gmail.com");
        user.setPassword("123");
        user.setNick("bialy93");
        user.setFirstName("Jakub");
        user.setLastName("Bialy");
        user.setEnabled(true);
        return user;
    }

    private User testUser2() {
        User user = new User();
        user.setUsername("andrzej@gmail.com");
        user.setPassword("123");
        user.setNick("Andrzej93");
        user.setFirstName("Andrzej");
        user.setLastName("Andrzejjj");
        user.setEnabled(true);
        return user;
    }

    private User testAdmin1() {
        User user = new User();
        user.setUsername("admin@admin.com");
        user.setPassword("123");
        user.setNick("adminAdmin");
        user.setFirstName("Admin");
        user.setLastName("Adminowicz");
        user.setEnabled(true);
        return user;
    }
}
