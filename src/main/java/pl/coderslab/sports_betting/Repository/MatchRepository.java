package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Match;

import java.time.LocalDateTime;
import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findAllByStartIsGreaterThan(LocalDateTime localDateTime);
    List<Match> findAllByEndIsGreaterThan(LocalDateTime localDateTime);
    List<Match> findAllByEndIsLessThan(LocalDateTime localDateTime);

}