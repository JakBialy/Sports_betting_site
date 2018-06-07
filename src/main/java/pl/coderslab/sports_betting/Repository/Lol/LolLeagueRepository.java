package pl.coderslab.sports_betting.Repository.Lol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_betting.Entity.Lol.LolLeague;

import java.util.List;

@Repository
public interface LolLeagueRepository extends JpaRepository<LolLeague, Long> {
    LolLeague findOneByName(String name);
}
