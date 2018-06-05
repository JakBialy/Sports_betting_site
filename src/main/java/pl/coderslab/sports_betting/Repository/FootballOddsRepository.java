package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.FootballOdds;

public interface FootballOddsRepository extends JpaRepository<FootballOdds, Long> {
}
