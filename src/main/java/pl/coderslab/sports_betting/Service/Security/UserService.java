package pl.coderslab.sports_betting.Service.Security;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.sports_betting.Entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    long getNumUsers();

    List<User> findAll();

    User findByUserName(String name);

    void saveUser(User user);

    void saveAdmin(User user);

    void userDetailsToSession (HttpSession httpSession);

    public void editUser(String username, String firstName, String lastName, String nick, String password);

    public void deleteUser(Long id);

    public void addToFavorites(Long id);

    public void removeFromFavorite(Long id);

    public void addToFriends(Long id);
}
