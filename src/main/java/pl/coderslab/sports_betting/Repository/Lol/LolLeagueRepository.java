package pl.coderslab.sports_betting.Repository.Lol;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Lol.LolLeague;

import java.util.List;

public interface LolLeagueRepository extends JpaRepository<LolLeague, Long> {
    LolLeague findOneByName(String name);
}
