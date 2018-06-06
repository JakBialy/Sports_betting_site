package pl.coderslab.sports_betting.Repository.Fotball;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface FootballBetRepository extends JpaRepository<FootballBet, Long> {
    List<FootballBet> findAllByFootballMatchEndIsGreaterThan(LocalDateTime localDateTime);
    List<FootballBet> findAllByFootballMatchEndIsLessThanAndFootballMatchCheckedIsFalse(LocalDateTime localDateTime);
    List<FootballBet> findAllByFootballMatch_StatusIsNotLike(String notFinished);
    List<FootballBet> findAllByUser (User user);
    List<FootballBet> findAllByUserId (Long Id);


}
