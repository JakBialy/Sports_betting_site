package pl.coderslab.sports_betting.Repository.Fotball;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Football.FootballOdds;

public interface FootballOddsRepository extends JpaRepository<FootballOdds, Long> {
}