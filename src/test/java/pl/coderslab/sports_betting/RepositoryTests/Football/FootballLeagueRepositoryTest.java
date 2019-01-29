package pl.coderslab.sports_betting.RepositoryTests.Football;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.sports_betting.Entity.Football.Country;
import pl.coderslab.sports_betting.Entity.Football.FootballLeague;
import pl.coderslab.sports_betting.Repository.Football.FootballLeagueRepository;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class FootballLeagueRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    FootballLeagueRepository footballBetRepository;

    @Test
    public void shouldFindByName(){
        FootballLeague footballLeague = new FootballLeague();
        footballLeague.setName("Random");

        entityManager.persist(footballLeague);

        FootballLeague result = footballBetRepository.findOneByName("Random");
        assertEquals("Random", result.getName());
    }

    @Test
    public void shouldFindByCountry(){
        Country country = new Country();
        country.setName("Randomia");

        FootballLeague footballLeague1 = new FootballLeague();
        footballLeague1.setName("Random");
        footballLeague1.setCountry(country);

        FootballLeague footballLeague2 = new FootballLeague();
        footballLeague2.setName("Homom");
        footballLeague2.setCountry(country);

        entityManager.persist(country);
        entityManager.persist(footballLeague1);
        entityManager.persist(footballLeague2);

        List<FootballLeague> result = footballBetRepository.findAllByCountry_Id(country.getId());
        assertEquals(2, result.size());

    }
}
