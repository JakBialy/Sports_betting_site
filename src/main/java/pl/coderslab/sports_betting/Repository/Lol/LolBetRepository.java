package pl.coderslab.sports_betting.Repository.Lol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_betting.Entity.Lol.LolBet;

import pl.coderslab.sports_betting.Entity.User;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LolBetRepository extends JpaRepository<LolBet, Long> {
    List<LolBet> findAllByUser(User user);
    List<LolBet> findAllByUserId(Long Id);
    List<LolBet> findAllByLolMatchEndIsLessThanAndLolMatchCheckedIsFalse(LocalDateTime localDateTime);

}
