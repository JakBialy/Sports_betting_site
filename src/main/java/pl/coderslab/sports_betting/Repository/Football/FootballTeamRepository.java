package pl.coderslab.sports_betting.Repository.Football;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_betting.Entity.Football.FootballTeam;

import java.util.List;

@Repository
public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {
    List<FootballTeam> findAllByFootballLeagueName(String name);
    List<FootballTeam> findAllByFootballLeagueIdOrderByPosition(Long id);
}
