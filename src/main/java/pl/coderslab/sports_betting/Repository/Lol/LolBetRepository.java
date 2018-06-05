package pl.coderslab.sports_betting.Repository.Lol;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Lol.LolBet;

import pl.coderslab.sports_betting.Entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface LolBetRepository extends JpaRepository<LolBet, Long> {
    List<LolBet> findAllByLolMatchEndIsGreaterThan(LocalDateTime localDateTime);
    List<LolBet> findAllByLolMatch_StatusIsNotLike(String notFinished);
    List<LolBet> findAllByUser(User user);
    List<LolBet> findAllByUserId(Long Id);


}
