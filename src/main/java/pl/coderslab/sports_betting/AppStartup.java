package pl.coderslab.sports_betting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Service.UserService;

@Component
public class AppStartup implements ApplicationRunner {

    private final UserService userService;

    @Autowired
    public AppStartup(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userService.getNumUsers() == 0L) {
            userService.saveUser(testUser1());
        }
    }

    private User testUser1() {
        User user = new User();
        user.setUsername("user1@user.pl");
        user.setPassword("user123");
        user.setNick("user1");
        user.setFirstName("Andrzej");
        user.setLastName("Andrzejo");
        user.setEnabled(true);
        return user;
    }

}
