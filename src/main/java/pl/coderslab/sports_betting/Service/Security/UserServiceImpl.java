package pl.coderslab.sports_betting.Service.Security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Role;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.UserRepository;


import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final String DEFAULT_USER_ROLE_NAME = "USER";

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
    public void editUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void userDetailsToSession (HttpSession httpSession){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        httpSession.setAttribute("firstName", user.getFirstName());
        httpSession.setAttribute("lastName", user.getLastName());
        httpSession.setAttribute("nick", user.getNick());
    }
}
