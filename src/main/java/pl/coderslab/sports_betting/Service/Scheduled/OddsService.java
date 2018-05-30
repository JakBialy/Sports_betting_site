package pl.coderslab.sports_betting.Service.Scheduled;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Match;
import pl.coderslab.sports_betting.Entity.Odds;
import pl.coderslab.sports_betting.Repository.MatchRepository;
import pl.coderslab.sports_betting.Repository.OddsRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class OddsService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    OddsRepository oddsRepository;

    @Scheduled(fixedRate = 1000*60*10, initialDelay = 1000)
    public void makeOdds() {
        List<Match> list = matchRepository.findAllByStartIsGreaterThan(LocalDateTime.now());
        for (Match load: list) {
            Double random1 = 1 + new Random().nextDouble() * (4 - 1);
            Double random2 = 1 + new Random().nextDouble() * (4 - 1);
            Double random3 = 1 + new Random().nextDouble() * (4 - 1);
            Double random4 = 1 + new Random().nextDouble() * (4 - 1);
            Double random5 = 1 + new Random().nextDouble() * (4 - 1);

            Odds odd = new Odds();
            odd.setOddHome(DoubleRounder.round(random1, 2));
            odd.setOddHomeHalf(DoubleRounder.round(random2, 2));

            odd.setOddX(DoubleRounder.round(random3, 2));

            odd.setOddAway(DoubleRounder.round(random4, 2));
            odd.setOddAwayHalf(DoubleRounder.round(random5, 2));

            odd.setBookmaker("Random");
            odd.setMatch(load);
            oddsRepository.save(odd);
        }
        System.out.println("Another set of Odds started! " + LocalDateTime.now().toString());
    }
}
