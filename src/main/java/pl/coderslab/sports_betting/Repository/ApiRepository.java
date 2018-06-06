package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.KeyApi;

public interface ApiRepository extends JpaRepository<KeyApi, Long> {
    KeyApi findOneByCode(String key);
}
