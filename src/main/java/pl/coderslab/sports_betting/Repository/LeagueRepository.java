package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.League;

public interface LeagueRepository extends JpaRepository<League, Long> {
}
