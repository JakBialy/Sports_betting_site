package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
