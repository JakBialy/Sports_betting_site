package pl.coderslab.sports_betting.Service.Scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.Odds;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.BetRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ScheduledBetService {

    @Autowired
    BetRepository betRepository;
    @Autowired
    UserRepository userRepository;

    @Scheduled(cron = ("59 4,9,14,19,24,29,34,39,44,49,54,59 * * * ?"))
    public void checkingBets(){
        List<Bet> betList = betRepository.findAllByMatchEndIsGreaterThan(LocalDateTime.now());
        for (Bet bet: betList) {
            Odds odds = bet.getMatch().getOdds();
            User user = bet.getUser();

            if(bet.getType().equals("homeWin")){
                if(bet.getMatch().getWinner() == bet.getMatch().getHomeTeam()){
                    bet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(odds.getOddHome());
                    moneyToUser(bet, user, winOdd);
                }

            } else if(bet.getType().equals("awayWin")){
                if(bet.getMatch().getWinner() == bet.getMatch().getAwayTeam()){
                    bet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(odds.getOddAway());
                    moneyToUser(bet, user, winOdd);
                }

            } else if(bet.getType().equals("Draw")){
                if(bet.getMatch().getWinner() == null){
                    bet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(odds.getOddX());
                    moneyToUser(bet, user, winOdd);
                }
            }
            betRepository.save(bet);
            userRepository.save(user);
        }
        System.out.println("Bets checked" + LocalDateTime.now());
    }

    private void moneyToUser(Bet bet, User user, BigDecimal winOdd) {
        BigDecimal money = bet.getMoney();
        BigDecimal bounty = winOdd.multiply(money);
        bounty = bounty.setScale(2, RoundingMode.CEILING);
        user.setMoney(user.getMoney().add(bounty));
    }
}
