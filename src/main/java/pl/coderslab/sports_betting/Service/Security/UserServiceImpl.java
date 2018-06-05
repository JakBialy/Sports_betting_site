package pl.coderslab.sports_betting.Service.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Role;
import pl.coderslab.sports_betting.Entity.Team;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.TeamRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    TeamRepository teamRepository;

    private static final String DEFAULT_USER_ROLE_NAME = "ROLE_USER";
    private static final String ADMIN = "ROLE_ADMIN";


    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public long getNumUsers() {
        return userRepository.count();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setMoney(BigDecimal.valueOf(100));

        Role role = roleService.getOrCreate(DEFAULT_USER_ROLE_NAME);
        Set<Role> roles = new HashSet<>(Collections.singletonList(role));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setMoney(BigDecimal.valueOf(99999999));
        Role role = roleService.getOrCreate(ADMIN);
        Set<Role> roles = new HashSet<>(Collections.singletonList(role));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public void editUser(String username, String firstName, String lastName, String nick, String password){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setNick(nick);
        user.setPassword(password);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void userDetailsToSession (HttpSession httpSession){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        httpSession.setAttribute("firstName", user.getFirstName());
        httpSession.setAttribute("lastName", user.getLastName());
        httpSession.setAttribute("nick", user.getNick());
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public void addToFavorites(Long id){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Team> teams = user.getFavoriteTeams();
        Team team = teamRepository.getOne(id);
        if (!(teams.contains(team))){
            teams.add(team);
            user.setFavoriteTeams(teams);
            userRepository.save(user);
        }
    }

    @Override
    public void removeFromFavorite(Long id){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<Team> teams = user.getFavoriteTeams();
        Team team = teamRepository.getOne(id);
        teams.remove(team);
        user.setFavoriteTeams(teams);
        userRepository.save(user);
    }

    public void addToFriends(Long id){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<User> friends = user.getFriends();
        User friend = userRepository.getOne(id);
        if (!(friends.contains(friend))){
            friends.add(friend);
            user.setFriends(friends);
            userRepository.save(user);
        }
    }
}
