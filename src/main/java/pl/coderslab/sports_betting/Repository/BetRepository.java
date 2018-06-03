package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Bet;

public interface BetRepository extends JpaRepository<Bet, Long> {
}
