package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.User;

import java.util.List;

public interface FootballBetService {
     void saveBet(FootballBet footballBet);
     List<FootballBet> findAllByUser(User user);
     List<FootballBet> findAllByUserId(Long userId);
     List<FootballBet> findAllUnacceptedByExtraUser();
     FootballBet findByUserId(Long userId);
}
