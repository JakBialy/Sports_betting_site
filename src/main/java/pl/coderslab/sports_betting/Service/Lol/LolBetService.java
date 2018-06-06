package pl.coderslab.sports_betting.Service.Lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Lol.LolBetRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LolBetService {
    @Autowired
    LolBetRepository lolBetRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveBet(LolBet lolBet){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setMoney(user.getMoney().subtract(lolBet.getMoney()));

        lolBet.setUser(user);
        lolBet.setDate(LocalDateTime.now());
        if(lolBet.getType().equals("homeWin")){
            lolBet.setLolTeam(lolBet.getLolMatch().getHomeLolTeam());
        } else if (lolBet.getType().equals("awayWin")){
            lolBet.setLolTeam(lolBet.getLolMatch().getAwayLolTeam());
        }
        lolBetRepository.save(lolBet);
    }

    public List<LolBet> findAllByUser(User user){
        List<LolBet> list = lolBetRepository.findAllByUser(user);
        return list;
    }

    public List<LolBet> findAllByUserId(Long userId){
        List<LolBet> list = lolBetRepository.findAllByUserId(userId);
        return list;
    }
}
