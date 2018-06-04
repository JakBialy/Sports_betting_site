package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Bet;
import pl.coderslab.sports_betting.Entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findAllByMatchEndIsGreaterThan (LocalDateTime localDateTime);
    List<Bet> findAllByMatch_StatusIsNotLike (String notFinished);
    List<Bet> findAllByUser (User user);
    List<Bet> findAllByUserId (Long Id);


}
