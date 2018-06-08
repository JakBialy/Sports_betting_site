package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.User;

import java.util.List;

public interface FootballBetService {

    public void saveBet(FootballBet footballBet);

    public List<FootballBet> findAllByUser(User user);

    public List<FootballBet> findAllByUserId(Long userId);

    public List<FootballBet> findAllUnacceptedByExtraUser();

    public FootballBet findByUserId(Long userId);

    }
