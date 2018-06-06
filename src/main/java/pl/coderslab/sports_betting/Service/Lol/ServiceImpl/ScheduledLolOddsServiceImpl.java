package pl.coderslab.sports_betting.Service.Lol.ServiceImpl;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolMatch;
import pl.coderslab.sports_betting.Entity.Lol.LolOdds;
import pl.coderslab.sports_betting.Repository.Lol.LolMatchRepository;
import pl.coderslab.sports_betting.Repository.Lol.LolOddsRepository;
import pl.coderslab.sports_betting.Service.Lol.Service.ScheduledLollOddsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class ScheduledLolOddsServiceImpl implements ScheduledLollOddsService {

    @Autowired
    LolMatchRepository lolMatchRepository;

    @Autowired
    LolOddsRepository lolOddsRepository;

    @Scheduled(cron = ("1 0/5 * 1/1 * ?"))
    public void makeOdds() {
        List<LolMatch> list = lolMatchRepository.findAllByStartIsGreaterThan(LocalDateTime.now());
        for (LolMatch load: list) {
            Double random1 = 1 + new Random().nextDouble() * (4 - 1);
            Double random2 = 1 + new Random().nextDouble() * (4 - 1);
            Double random3 = 1 + new Random().nextDouble() * (4 - 1);
            Double random4 = 1 + new Random().nextDouble() * (4 - 1);
            Double random5 = 1 + new Random().nextDouble() * (4 - 1);

            LolOdds odd = new LolOdds();
            odd.setOddHome(DoubleRounder.round(random1, 2));
            odd.setOddAway(DoubleRounder.round(random4, 2));

            odd.setBookmaker("Random");
            odd.setLolMatch(load);
            lolOddsRepository.save(odd);
        }
        System.out.println("Another set of LolOdds started! " + LocalDateTime.now().toString());
    }
}
