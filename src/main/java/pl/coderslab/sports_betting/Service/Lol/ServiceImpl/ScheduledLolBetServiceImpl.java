package pl.coderslab.sports_betting.Service.Lol.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.Lol.LolMatch;
import pl.coderslab.sports_betting.Entity.Lol.LolOdds;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Lol.LolBetRepository;
import pl.coderslab.sports_betting.Repository.General.UserRepository;
import pl.coderslab.sports_betting.Service.Lol.Service.ScheduledLolBetService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ScheduledLolBetServiceImpl implements ScheduledLolBetService {

    @Autowired
    LolBetRepository lolBetRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
//    @Scheduled(cron = ("59 4,9,14,19,24,29,34,39,44,49,54,59 * * * ?"))
    @Scheduled(cron = ("1 0/5 * 1/1 * ?"))
    public void checkingBets(){
//        List<LolBet> lolBetList = lolBetRepository.findAllByLolMatchEndIsGreaterThan(LocalDateTime.now());
        List<LolBet> lolBetList = lolBetRepository.findAllByLolMatchEndIsLessThanAndLolMatchCheckedIsFalse(LocalDateTime.now());

        for (LolBet lolBet : lolBetList) {
            LolOdds lolOdds = lolBet.getLolMatch().getLolOdds();
            User user = lolBet.getUser();

            if(lolBet.getType().equals("firstWin")){
                if(lolBet.getLolMatch().getWinner() == lolBet.getLolMatch().getHomeLolTeam()){
                    lolBet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(lolOdds.getOddHome());
                    moneyToUser(lolBet, user, winOdd);
                } else {
                    lolBet.setWinner(false);
                }

            } else if(lolBet.getType().equals("secondWin")){
                if(lolBet.getLolMatch().getWinner() == lolBet.getLolMatch().getAwayLolTeam()){
                    lolBet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(lolOdds.getOddAway());
                    moneyToUser(lolBet, user, winOdd);
                } else {
                    lolBet.setWinner(false);
                }
            }
            LolMatch match = lolBet.getLolMatch();
            match.setChecked(true);
            lolBetRepository.save(lolBet);
            userRepository.save(user);
        }
        System.out.println("Bets checked" + LocalDateTime.now());
    }

    public void moneyToUser(LolBet lolBet, User user, BigDecimal winOdd) {
        BigDecimal money = lolBet.getMoney();
        BigDecimal bounty = winOdd.multiply(money);
        bounty = bounty.setScale(2, RoundingMode.CEILING);
        user.setMoney(user.getMoney().add(bounty));
    }
}
