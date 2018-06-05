package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.FootballTeam;

import java.util.List;

public interface FootballTeamRepository extends JpaRepository<FootballTeam, Long> {
    List<FootballTeam> findAllByFootballLeagueName(String name);
    List<FootballTeam> findAllByFootballLeagueIdOrderByPosition(Long id);
}
