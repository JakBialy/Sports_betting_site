package pl.coderslab.sports_betting.Repository.Lol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_betting.Entity.Lol.LolMatch;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LolMatchRepository extends JpaRepository<LolMatch, Long> {
    List<LolMatch> findAllByStartIsGreaterThan(LocalDateTime localDateTime);
    List<LolMatch> findAllByEndIsGreaterThan(LocalDateTime localDateTime);
    List<LolMatch> findAllByEndIsLessThan(LocalDateTime localDateTime);
    List<LolMatch> findAllByStatus(String status);
    List<LolMatch> findAllByHomeLolTeamIdOrderByStart(Long id);
    List<LolMatch> findAllByAwayLolTeamIdOrderByStart(Long id);
    List<LolMatch> findAllByStartIsBetween(LocalDateTime localDateTime1, LocalDateTime localDateTime2);

}