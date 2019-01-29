package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Football.FootballBetRepository;
import pl.coderslab.sports_betting.Repository.General.UserRepository;
import pl.coderslab.sports_betting.Service.Football.Service.FootballBetService;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FootballBetServiceImpl implements FootballBetService {
    @Autowired
    FootballBetRepository footballBetRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Method is saving bet, first is looking if status of match is equal to "planned"
     * if it is true then is looking for active user, subtract money from user account, then
     * is setting user and local time, depending on type of bet is saving odd and/or team,
     * finally save bet to database
     * @param footballBet object of footballBet
     *
     */
    @Transactional
    public void saveBet(FootballBet footballBet){
        if (footballBet.getFootballMatch().getStatus().equals("planned")) {

            User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            user.setMoney(user.getMoney().subtract(footballBet.getMoney()));
            footballBet.setUser(user);
            footballBet.setDate(LocalDateTime.now());
            if (footballBet.getType().equals("homeWin")) {
                footballBet.setFootballTeam(footballBet.getFootballMatch().getHomeFootballTeam());
                footballBet.setOdd(BigDecimal.valueOf(footballBet.getFootballMatch().getFootballOdds().getOddHome()));
            } else if (footballBet.getType().equals("awayWin")) {
                footballBet.setFootballTeam(footballBet.getFootballMatch().getAwayFootballTeam());
                footballBet.setOdd(BigDecimal.valueOf(footballBet.getFootballMatch().getFootballOdds().getOddAway()));
            } else {
                footballBet.setOdd(BigDecimal.valueOf(footballBet.getFootballMatch().getFootballOdds().getOddX()));
            }
            footballBetRepository.save(footballBet);
        }
    }

    /**
     * Method is finding all bets by object User
     * @param user selected object User
     * @return list selected user bets
     */
    public List<FootballBet> findAllByUser(User user){
        List<FootballBet> list = footballBetRepository.findAllByUser(user);
        return list;
    }

    /**
     * Method is finding all bets by user id
     * @param userId selected  userId
     * @return list of selected user bets
     */
    public List<FootballBet> findAllByUserId(Long userId){
        List<FootballBet> list = footballBetRepository.findAllByUserId(userId);
        return list;
    }

    public FootballBet findByUserId(Long userId){
        FootballBet bet = footballBetRepository.getOne(userId);
        return bet;
    }

    public List<FootballBet> findAllUnacceptedByExtraUser(){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        List<FootballBet> list = footballBetRepository.findAllByAcceptedIsFalseAndExtraIs(user);
        return list;
    }
}
