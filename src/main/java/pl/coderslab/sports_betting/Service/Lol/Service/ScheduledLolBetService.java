package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.User;

import java.math.BigDecimal;

public interface ScheduledLolBetService {

    public void checkingBets();

    public void moneyToUser(LolBet lolBet, User user, BigDecimal winOdd);

    }
