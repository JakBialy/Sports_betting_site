package pl.coderslab.sports_betting.Service.Scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.FootballOdds;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.BetRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;

import java.math.BigDecimal;
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
        List<Bet> betList = betRepository.findAllByFootballMatchEndIsGreaterThan(LocalDateTime.now());
        for (Bet bet: betList) {
            FootballOdds footballOdds = bet.getFootballMatch().getFootballOdds();
            User user = bet.getUser();

            if(bet.getType().equals("homeWin")){
                if(bet.getFootballMatch().getWinner() == bet.getFootballMatch().getHomeFootballTeam()){
                    bet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(footballOdds.getOddHome());
                    moneyToUser(bet, user, winOdd);
                }

            } else if(bet.getType().equals("awayWin")){
                if(bet.getFootballMatch().getWinner() == bet.getFootballMatch().getAwayFootballTeam()){
                    bet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(footballOdds.getOddAway());
                    moneyToUser(bet, user, winOdd);
                }

            } else if(bet.getType().equals("Draw")){
                if(bet.getFootballMatch().getWinner() == null){
                    bet.setWinner(true);
                    BigDecimal winOdd = BigDecimal.valueOf(footballOdds.getOddX());
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
