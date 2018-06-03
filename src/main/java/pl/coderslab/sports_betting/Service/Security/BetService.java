package pl.coderslab.sports_betting.Service.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.BetRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;

import java.time.LocalDateTime;

@Service
public class BetService {
    @Autowired
    BetRepository betRepository;

    @Autowired
    UserRepository userRepository;

    public void saveBet(Bet bet){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        bet.setUser(user);
        bet.setDate(LocalDateTime.now());
        betRepository.save(bet);
    }
}
