package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.Football.FootballMatch;
import pl.coderslab.sports_betting.Entity.Football.FootballOdds;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Football.FootballBetRepository;
import pl.coderslab.sports_betting.Repository.General.UserRepository;
import pl.coderslab.sports_betting.Service.Football.Service.ScheduledFootballBetService;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ScheduledFootballBetServiceImpl implements ScheduledFootballBetService {

    @Autowired
    FootballBetRepository footballBetRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * Method is checking football bets every full 5 minutes(ex 5:00, 10:00)
     * First is looking for matches which are finished and not checked by this method
     * then for all bets it is getting: odds and users
     * every football bet is checked by type and if winner team saved in bet is
     * the same like real winner (or is null in case of draw) then bet is checked as a winner
     * and money is added to user, otherwise bet(winner) is checked as a false
     * In the end bet status checked is changed into true and football bet and user are saved into db
     *
     * As a method is important for system and must be fully executed is with annotation @Transactional
     */
    @Transactional
    @Scheduled(cron = ("1 0/5 * 1/1 * ?"))
    public void checkingBets(){
        List<FootballBet> footballBetList = footballBetRepository.findAllByFootballMatchEndIsLessThanAndFootballMatchCheckedIsFalseAndAcceptedIsTrue(LocalDateTime.now());

        for (FootballBet footballBet : footballBetList) {
            FootballOdds footballOdds = footballBet.getFootballMatch().getFootballOdds();
            User user = footballBet.getUser();

            if(footballBet.getType().equals("homeWin")){
                if(footballBet.getFootballMatch().getWinner() == footballBet.getFootballMatch().getHomeFootballTeam()){
                    footballBet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(footballOdds.getOddHome());
                    moneyToUser(footballBet, user, winOdd);
                } else {
                    footballBet.setWinner(false);
                }

            } else if(footballBet.getType().equals("awayWin")){
                if(footballBet.getFootballMatch().getWinner() == footballBet.getFootballMatch().getAwayFootballTeam()){
                    footballBet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(footballOdds.getOddAway());
                    moneyToUser(footballBet, user, winOdd);
                } else {
                    footballBet.setWinner(false);
                }

            } else if(footballBet.getType().equals("Draw")){
                if(footballBet.getFootballMatch().getWinner() == null){
                    footballBet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(footballOdds.getOddX());
                    moneyToUser(footballBet, user, winOdd);
                } else {
                    footballBet.setWinner(false);
                }
            }
            FootballMatch match = footballBet.getFootballMatch();
            match.setChecked(true);
            footballBetRepository.save(footballBet);
            userRepository.save(user);
        }
        System.out.println("Bets checked" + LocalDateTime.now());
    }

    /**
     * Methods used in checkingBets method for saving money into user object
     * @param footballBet actual football bet
     * @param user user which place a bet
     * @param winOdd apropiate odd which is applied to this bet
     */
    public void moneyToUser(FootballBet footballBet, User user, BigDecimal winOdd) {
        BigDecimal money = footballBet.getMoney();
        BigDecimal bounty = winOdd.multiply(money);
        bounty = bounty.setScale(2, RoundingMode.CEILING);
        user.setMoney(user.getMoney().add(bounty));
    }
}
