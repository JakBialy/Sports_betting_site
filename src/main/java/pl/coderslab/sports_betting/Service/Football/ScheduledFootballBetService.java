package pl.coderslab.sports_betting.Service.Football;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.Football.FootballMatch;
import pl.coderslab.sports_betting.Entity.Football.FootballOdds;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Fotball.FootballBetRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ScheduledFootballBetService {

    @Autowired
    FootballBetRepository footballBetRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
//    @Scheduled(cron = ("59 4,9,14,19,24,29,34,39,44,49,54,59 * * * ?"))

    @Scheduled(cron = ("1 0/5 * 1/1 * ?"))
    public void checkingBets(){
//        List<FootballBet> footballBetList = footballBetRepository.findAllByFootballMatchEndIsGreaterThan(LocalDateTime.now());

        List<FootballBet> footballBetList = footballBetRepository.findAllByFootballMatchEndIsLessThanAndFootballMatchCheckedIsFalse(LocalDateTime.now());

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

    private void moneyToUser(FootballBet footballBet, User user, BigDecimal winOdd) {
        BigDecimal money = footballBet.getMoney();
        BigDecimal bounty = winOdd.multiply(money);
        bounty = bounty.setScale(2, RoundingMode.CEILING);
        user.setMoney(user.getMoney().add(bounty));
    }
}
