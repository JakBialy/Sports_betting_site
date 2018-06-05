package pl.coderslab.sports_betting.Repository.Lol;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Lol.LolOdds;

public interface LolOddsRepository extends JpaRepository<LolOdds, Long> {
}
