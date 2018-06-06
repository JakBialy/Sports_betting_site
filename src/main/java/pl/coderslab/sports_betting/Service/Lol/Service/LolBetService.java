package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolBet;
import pl.coderslab.sports_betting.Entity.User;

import java.util.List;

public interface LolBetService {

    public void saveBet(LolBet lolBet);

    public List<LolBet> findAllByUser(User user);

    public List<LolBet> findAllByUserId(Long userId);

    }
