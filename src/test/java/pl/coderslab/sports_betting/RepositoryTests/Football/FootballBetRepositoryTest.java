package pl.coderslab.sports_betting.RepositoryTests.Football;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
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
    public void shouldFindBetsUserById(){
        User user = new User();
        user.setId(1L);
//        entityManager.persist(user);
        List<FootballBet> list = new ArrayList<>();
        FootballBet bet1 = new FootballBet();
        FootballBet bet2 = new FootballBet();
        bet1.setUser(user);
        bet1.setType("Draw");
        bet1.setMoney(BigDecimal.valueOf(10));
        bet2.setUser(user);
        bet2.setMoney(BigDecimal.valueOf(10));
        bet2.setType("Draw");
        bet1.setMoney(BigDecimal.valueOf(10));

        entityManager.persist(bet1);
        entityManager.persist(bet2);
//        entityManager.persist(list);

        List<FootballBet> result = footballBetRepository.findAllByUser(user);
        assertEquals(2, result.size());
    }
}
