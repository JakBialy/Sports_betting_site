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
    private final LolMatchRepository lolMatchRepository;
    private final LolOddsRepository lolOddsRepository;

    @Autowired
    public ScheduledLolOddsServiceImpl(LolMatchRepository lolMatchRepository, LolOddsRepository lolOddsRepository) {
        this.lolMatchRepository = lolMatchRepository;
        this.lolOddsRepository = lolOddsRepository;
    }

    /**
     * After full 5 minutes method is generating odds
     * First taking all foll matches
     * then is generating five random doubles from 1 to 4
     * random double are set into home odd, odd home half, odd X(draw), odd away and odd away half
     * and rounded with precision of 2
     * bookmaker is set as a random
     * fol odds are saved into databsae
     */
    @Scheduled(cron = ("1 0/5 * 1/1 * ?"))
    public void makeOdds() {
        List<LolMatch> list = lolMatchRepository.findAllByStartIsGreaterThan(LocalDateTime.now());
        for (LolMatch load: list) {
            double random1 = 1 + new Random().nextDouble() * (4 - 1);
            double random2 = 1 + new Random().nextDouble() * (4 - 1);
            double random3 = 1 + new Random().nextDouble() * (4 - 1);
            double random4 = 1 + new Random().nextDouble() * (4 - 1);
            double random5 = 1 + new Random().nextDouble() * (4 - 1);

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
