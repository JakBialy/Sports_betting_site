package pl.coderslab.sports_betting.Service.Lol.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Lol.LolBetRepository;
import pl.coderslab.sports_betting.Repository.General.UserRepository;
import pl.coderslab.sports_betting.Service.Lol.Service.LolBetService;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LolBetServiceImpl implements LolBetService {
    @Autowired
    LolBetRepository lolBetRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Method is saving bet, first is looking if status of match is equal to "planned"
     * if it is true then is looking for active user, subtract money from user account, then
     * is setting user and local time, then is saving odd and team,
     * finally save bet to database
     * @param lolBet object of lolBet
     *
     */
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

    /**
     * Method is finding all bets by object User
     * @param user selected object User
     * @return list selected user bets
     */
    public List<LolBet> findAllByUser(User user){
        List<LolBet> list = lolBetRepository.findAllByUser(user);
        return list;
    }

    /**
     * Method is finding all bets by user id
     * @param userId selected  userId
     * @return list of selected user bets
     */
    public List<LolBet> findAllByUserId(Long userId){
        List<LolBet> list = lolBetRepository.findAllByUserId(userId);
        return list;
    }
}
