package pl.coderslab.sports_betting.Repository.Fotball;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Football.FootballMatch;

import java.time.LocalDateTime;
import java.util.List;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {
    List<FootballMatch> findAllByStartIsGreaterThan(LocalDateTime localDateTime);
    List<FootballMatch> findAllByEndIsGreaterThan(LocalDateTime localDateTime);
    List<FootballMatch> findAllByEndIsLessThanAndStatusIsFalse(LocalDateTime localDateTime);
    List<FootballMatch> findAllByStatus (String status);
    List<FootballMatch> findAllByHomeFootballTeamIdOrderByStart(Long id);
    List<FootballMatch> findAllByAwayFootballTeamIdOrderByStart(Long id);
    List<FootballMatch> findAllByStartIsBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

}