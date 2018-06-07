package pl.coderslab.sports_betting.Repository.Lol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_betting.Entity.Lol.LolTeam;

import java.util.List;

@Repository
public interface LolTeamRepository extends JpaRepository<LolTeam, Long> {
    List<LolTeam> findAllByLolLeagueName(String name);
    List<LolTeam> findAllByLolLeagueIdOrderByPosition(Long id);
}
