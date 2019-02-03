package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.User;

import java.util.List;

public interface LolBetService {
     void saveBet(LolBet lolBet);
     List<LolBet> findAllByUser(User user);
     List<LolBet> findAllByUserId(Long userId);
}
