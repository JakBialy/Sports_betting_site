package pl.coderslab.sports_betting.Service.Security.Service;

import pl.coderslab.sports_betting.Entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    long getNumUsers();
    List<User> findAll();
    User findByUserName(String name);
    User findById(Long id);
    void saveUser(User user);
    void saveAdmin(User user);
    void userDetailsToSession (HttpSession httpSession);
    void editUser(String username, String firstName, String lastName, String nick, String password);
    void deleteUser(Long id);
    void addToFootballFavorites(Long id);
    void removeFromFootballFavorite(Long id);
    void removeFromLolFavorite(Long id);
    void addToFriends(Long id);
    List<User> findAllWithoutActiveUser();
    List<User> getAllUserFriends();
    void addToLolFavorites(Long id);
    String checkFavorite();
    }
