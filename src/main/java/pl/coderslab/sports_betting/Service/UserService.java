package pl.coderslab.sports_betting.Service;

import pl.coderslab.sports_betting.Entity.User;

import java.util.List;

public interface UserService {

    long getNumUsers();

    List<User> findAll();

    User findByUserName(String name);

    void saveUser(User user);
}
