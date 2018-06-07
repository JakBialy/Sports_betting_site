package pl.coderslab.sports_betting.Repository.General;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_betting.Entity.Football.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findOneByName(String name);
}
