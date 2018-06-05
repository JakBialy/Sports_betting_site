package pl.coderslab.sports_betting.Service.Football;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Fotball.FootballBetRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FootballBetService {
    @Autowired
    FootballBetRepository footballBetRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveBet(FootballBet footballBet){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setMoney(user.getMoney().subtract(footballBet.getMoney()));

        footballBet.setUser(user);
        footballBet.setDate(LocalDateTime.now());
        if(footballBet.getType().equals("homeWin")){
            footballBet.setFootballTeam(footballBet.getFootballMatch().getHomeFootballTeam());
        } else if (footballBet.getType().equals("awayWin")){
            footballBet.setFootballTeam(footballBet.getFootballMatch().getAwayFootballTeam());
        }
        footballBetRepository.save(footballBet);
    }

    public List<FootballBet> findAllByUser(User user){
        List<FootballBet> list = footballBetRepository.findAllByUser(user);
        return list;
    }

    public List<FootballBet> findAllByUserId(Long userId){
        List<FootballBet> list = footballBetRepository.findAllByUserId(userId);
        return list;
    }
}
