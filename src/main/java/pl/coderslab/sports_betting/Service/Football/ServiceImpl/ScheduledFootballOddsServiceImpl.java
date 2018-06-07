package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Football.FootballMatch;
import pl.coderslab.sports_betting.Entity.Football.FootballOdds;
import pl.coderslab.sports_betting.Repository.Fotball.FootballMatchRepository;
import pl.coderslab.sports_betting.Repository.Fotball.FootballOddsRepository;
import pl.coderslab.sports_betting.Service.Football.Service.ScheduledFootballOddsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class ScheduledFootballOddsServiceImpl implements ScheduledFootballOddsService {

    @Autowired
    FootballMatchRepository footballMatchRepository;

    @Autowired
    FootballOddsRepository footballOddsRepository;

    /**
     * After full 5 minutes method is generating odds
     * First taking all football matches
     * then is generating five random doubles from 1 to 3
     * random double are set into home odd, odd home half, odd X(draw), odd away and odd away half
     * and rounded with precision of 2
     * bookmaker is set as a random
     * footbal odds are saved into databsae
     */
    @Scheduled(cron = ("1 0/5 * 1/1 * ?"))
    public void makeOdds() {
        List<FootballMatch> list = footballMatchRepository.findAllByStartIsGreaterThan(LocalDateTime.now());
        for (FootballMatch load: list) {
            Double random1 = 1 + new Random().nextDouble() * (4 - 1);
            Double random2 = 1 + new Random().nextDouble() * (4 - 1);
            Double random3 = 1 + new Random().nextDouble() * (4 - 1);
            Double random4 = 1 + new Random().nextDouble() * (4 - 1);
            Double random5 = 1 + new Random().nextDouble() * (4 - 1);

            FootballOdds odd = new FootballOdds();
            odd.setOddHome(DoubleRounder.round(random1, 2));
            odd.setOddHomeHalf(DoubleRounder.round(random2, 2));

            odd.setOddX(DoubleRounder.round(random3, 2));

            odd.setOddAway(DoubleRounder.round(random4, 2));
            odd.setOddAwayHalf(DoubleRounder.round(random5, 2));

            odd.setBookmaker("Random");
            odd.setFootballMatch(load);
            footballOddsRepository.save(odd);
        }
        System.out.println("Another set of FootballOdds started! " + LocalDateTime.now().toString());
    }
}
