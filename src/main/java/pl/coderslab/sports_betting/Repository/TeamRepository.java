package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findAllByLeague_Name (String name);
}
