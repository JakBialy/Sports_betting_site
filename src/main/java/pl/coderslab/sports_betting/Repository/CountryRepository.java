package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
