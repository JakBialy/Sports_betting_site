package pl.coderslab.sports_betting.Repository.Fotball;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Football.FootballLeague;

import java.util.List;

public interface FootballLeagueRepository extends JpaRepository<FootballLeague, Long> {
    FootballLeague findOneByName (String name);
    List<FootballLeague> findAllByCountry_Id(Long id);
}
