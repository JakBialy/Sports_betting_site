package pl.coderslab.sports_betting.Repository.Lol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_betting.Entity.Lol.LolOdds;

@Repository
public interface LolOddsRepository extends JpaRepository<LolOdds, Long> {
}
