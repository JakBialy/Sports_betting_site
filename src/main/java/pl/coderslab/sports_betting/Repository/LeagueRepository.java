package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.League;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {
    League findOneByName (String name);
    List<League> findAllByCountry_Id(Long id);
}
