package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.User;

import java.math.BigDecimal;

public interface ScheduledFootballBetService {

    public void checkingBets();

    public void moneyToUser(FootballBet footballBet, User user, BigDecimal winOdd);

    }
