package pl.coderslab.sports_betting.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.BetRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BetService {
    @Autowired
    BetRepository betRepository;

    @Autowired
    UserRepository userRepository;

    public void saveBet(Bet bet){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setMoney(user.getMoney().subtract(bet.getMoney()));

        bet.setUser(user);
        bet.setDate(LocalDateTime.now());
        if(bet.getType().equals("homeWin")){
            bet.setTeam(bet.getMatch().getHomeTeam());
        } else if (bet.getType().equals("awayWin")){
            bet.setTeam(bet.getMatch().getAwayTeam());
        }
        betRepository.save(bet);
    }

    public List<Bet> findAllByUser(User user){
        List<Bet> list = betRepository.findAllByUser(user);
        return list;
    }

    public List<Bet> findAllByUserId(Long userId){
        List<Bet> list = betRepository.findAllByUserId(userId);
        return list;
    }
}
