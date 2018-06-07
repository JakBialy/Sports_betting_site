package pl.coderslab.sports_betting.Repository.General;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.sports_betting.Entity.KeyApi;

@Repository
public interface ApiRepository extends JpaRepository<KeyApi, Long> {
    KeyApi findOneByCode(String key);
}
