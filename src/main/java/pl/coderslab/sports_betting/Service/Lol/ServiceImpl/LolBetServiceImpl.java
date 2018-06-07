package pl.coderslab.sports_betting.Service.Lol.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Lol.LolBetRepository;
import pl.coderslab.sports_betting.Repository.General.UserRepository;
import pl.coderslab.sports_betting.Service.Lol.Service.LolBetService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LolBetServiceImpl implements LolBetService {
    @Autowired
    LolBetRepository lolBetRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveBet(LolBet lolBet){
        if (lolBet.getLolMatch().getStatus().equals("planned")){

            User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            user.setMoney(user.getMoney().subtract(lolBet.getMoney()));
            lolBet.setUser(user);
            lolBet.setDate(LocalDateTime.now());

            if(lolBet.getType().equals("firstWin")){
                lolBet.setLolTeam(lolBet.getLolMatch().getHomeLolTeam());
                lolBet.setOdd(BigDecimal.valueOf(lolBet.getLolMatch().getLolOdds().getOddHome()));
            } else if (lolBet.getType().equals("secondWin")){
                lolBet.setLolTeam(lolBet.getLolMatch().getAwayLolTeam());
                lolBet.setOdd(BigDecimal.valueOf(lolBet.getLolMatch().getLolOdds().getOddAway()));
            }
            lolBetRepository.save(lolBet);
        }
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
