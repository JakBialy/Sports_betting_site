package pl.coderslab.sports_betting.RepositoryTests.Football;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.Football.FootballMatch;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.Fotball.FootballBetRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class FootballBetRepositoryTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    FootballBetRepository footballBetRepository;

    @Test
    public void shouldFindBetsUser(){
        User user = new User();
        // id is given by database
        user.setUsername("bbb@bbb.pl");
        user.setFirstName("AAA");
        user.setLastName("BBBB");
        user.setNick("CCC");
        user.setPassword("DDD");

        FootballBet bet1 = new FootballBet();
        FootballBet bet2 = new FootballBet();
        bet1.setUser(user);
        bet1.setType("Draw");
        bet1.setMoney(BigDecimal.valueOf(10));
        bet2.setUser(user);
        bet2.setMoney(BigDecimal.valueOf(10));
        bet2.setType("Draw");
        bet1.setMoney(BigDecimal.valueOf(10));

        entityManager.persist(user);
        entityManager.persist(bet1);
        entityManager.persist(bet2);

        List<FootballBet> result = footballBetRepository.findAllByUser(user);
        assertEquals(2, result.size());
    }

    @Test
    public void shouldFindBetsUserById(){
        User user = new User();
        // id is given by database
        user.setUsername("bbb@bbb.pl");
        user.setFirstName("AAA");
        user.setLastName("BBB");
        user.setNick("CCC");
        user.setPassword("DDD");

        FootballBet bet1 = new FootballBet();
        FootballBet bet2 = new FootballBet();
        bet1.setUser(user);
        bet1.setType("Draw");
        bet1.setMoney(BigDecimal.valueOf(10));
        bet2.setUser(user);
        bet2.setMoney(BigDecimal.valueOf(10));
        bet2.setType("Draw");
        bet1.setMoney(BigDecimal.valueOf(10));

        entityManager.persist(user);
        entityManager.persist(bet1);
        entityManager.persist(bet2);

        List<FootballBet> result = footballBetRepository.findAllByUserId(user.getId());
        assertEquals(2, result.size());
    }

    public void shouldNotFindFinished(){
        User user = new User();
        // id is given by database
        user.setUsername("bbb@bbb.pl");
        user.setFirstName("AAA");
        user.setLastName("BBB");
        user.setNick("CCC");
        user.setPassword("DDD");

        FootballBet bet1 = new FootballBet();
        FootballBet bet2 = new FootballBet();
        bet1.setUser(user);
        bet1.setType("Draw");
        bet1.setMoney(BigDecimal.valueOf(10));

        FootballMatch footballMatch2 = new FootballMatch();
        footballMatch2.setStatus("First Half");

        bet1.setFootballMatch(footballMatch2);

        bet2.setUser(user);
        bet2.setMoney(BigDecimal.valueOf(10));
        bet2.setType("Draw");

        FootballMatch footballMatch1 = new FootballMatch();
        footballMatch1.setStatus("finished");

        bet2.setFootballMatch(footballMatch1);

        entityManager.persist(bet1);
        entityManager.persist(bet2);
        entityManager.persist(footballMatch1);
        entityManager.persist(footballMatch2);

        List<FootballBet> result = footballBetRepository.findAllByFootballMatch_StatusIsNotLike("finished");
        assertEquals(1, result.size());

    }

}
