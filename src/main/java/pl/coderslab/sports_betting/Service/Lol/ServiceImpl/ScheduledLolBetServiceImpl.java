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

import org.springframework.transaction.annotation.Transactional;
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


    /**
     * Method is checking lol bets every full 5 minutes(ex 5:00, 10:00)
     * First is looking for matches which are finished and not checked by this method
     * then for all bets it is getting: odds and users
     * every lol bet is checked by type and if winner team saved in bet is
     * the same like real winner then bet is checked as a winner
     * and money is added to user, otherwise bet(winner) is checked as a false
     * In the end bet status checked is changed into true and lol bet and user are saved into db
     *
     * As a method is important for system and must be fully executed is with annotation @Transactional
     */
    @Transactional
    @Scheduled(cron = ("1 0/5 * 1/1 * ?"))
    public void checkingBets(){
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

    /**
     * Methods used in checkingBets method for saving money into user object
     * @param lolBet actual lol bet
     * @param user user which place a bet
     * @param winOdd appropriate odd which is applied to this bet
     */
    public void moneyToUser(LolBet lolBet, User user, BigDecimal winOdd) {
        BigDecimal money = lolBet.getMoney();
        BigDecimal bounty = winOdd.multiply(money);
        bounty = bounty.setScale(2, RoundingMode.CEILING);
        user.setMoney(user.getMoney().add(bounty));
    }
}
