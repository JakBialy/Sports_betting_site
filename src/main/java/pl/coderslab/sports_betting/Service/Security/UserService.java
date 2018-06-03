package pl.coderslab.sports_betting.Service.Security;

import pl.coderslab.sports_betting.Entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    long getNumUsers();

    List<User> findAll();

    User findByUserName(String name);

    void saveUser(User user);

    void userDetailsToSession (HttpSession httpSession);

    }
