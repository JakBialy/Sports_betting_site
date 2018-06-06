package pl.coderslab.sports_betting.Service.Lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolMatch;
import pl.coderslab.sports_betting.Entity.Lol.LolTeam;
import pl.coderslab.sports_betting.Repository.Lol.LolMatchRepository;
import pl.coderslab.sports_betting.Repository.Lol.LolTeamRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Comparator.comparing;

@Service
public class ScheduledLolMatchService {

    @Autowired
    LolTeamRepository lolTeamRepository;

    @Autowired
    LolMatchRepository lolMatchRepository;

    private static int LolStaticCounter = 0;

    @Scheduled(cron = ("0 0/5 * 1/1 * ?"))
    public void startMatches() {
        List<LolMatch> lolMatchList = new ArrayList<>();
        List<LolTeam> lolTeamList = new ArrayList<>();
        int counter = 0;

        for (int i = 1; i <= 2; i++) {
            if (i == 1) {
                lolTeamList = lolTeamRepository.findAllByLolLeagueName("EU West");
                Collections.shuffle(lolTeamList);

            } else {
                lolTeamList = lolTeamRepository.findAllByLolLeagueName("EU East & Nordic");
                Collections.shuffle(lolTeamList);
                counter = 0;
            }
            for (int j = 1; j <= 5; j++) {
                // shuffle teams to make them random every time
                LolMatch lolMatch = new LolMatch();
                lolMatch.setStart(LocalDateTime.now().plusMinutes(3));
                lolMatch.setEnd(LocalDateTime.now().plusMinutes(5));
                lolMatch.setHomeLolTeam(lolTeamList.get(counter));
                lolMatch.setAwayLolTeam(lolTeamList.get(1 + counter));
                lolMatch.setStatus("planned");
                lolMatchList.add(lolMatch);
                counter += 2;
            }
        }
        lolMatchRepository.saveAll(lolMatchList);
        System.out.println("Another set of matches going on! " + LocalDateTime.now().toString());
    }

    @Scheduled(cron = ("58 3,4,8,9,13,14,18,19,23,24,28,29,33,34,38,39,43,44,48,49,53,54,58,59 * * * ?"))
    public void goalsMaker() {
        List<LolMatch> list = lolMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        if (!(list.isEmpty())){
            LolStaticCounter++;
        }
        for (LolMatch lolMatch : list) {
            Random r = new Random();

            int random1 = r.nextInt(20) +1;
            int random2 = r.nextInt(20) +1;

            lolMatch.setAwayScore(lolMatch.getAwayScore() + random1);
                lolMatch.setHomeScore(lolMatch.getHomeScore() + random2);

            if ((LolStaticCounter % 2 == 0)) {
                lolMatch.setStatus("Full Time");
            }

            lolMatchRepository.save(lolMatch);
        }
        System.out.println("Another set of Lol results going on! " + LocalDateTime.now().toString());
    }

    @Scheduled(cron = ("0 3,8,13,18,23,28,33,38,43,48,53,58 * * * ?"))
    public void matchStartStatus() {
        List<LolMatch> lolMatchList1 = lolMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        for (LolMatch load : lolMatchList1) {
            load.setStatus("In game");
            lolMatchRepository.save(load);
        }
        System.out.println("LolMatch started!" + LocalDateTime.now().toString());
    }

    @Scheduled(cron = ("59 4,9,14,19,24,29,34,39,44,49,54,59 * * * ?"))
    public void matchesResult() {
        List<LolMatch> list = lolMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());

        for (LolMatch lolMatch : list) {
         int away = lolMatch.getAwayScore();
         int home = lolMatch.getHomeScore();
         LolTeam awayLolTeam = lolMatch.getAwayLolTeam();
         LolTeam homeLolTeam = lolMatch.getHomeLolTeam();

         if (away>home){
             lolMatch.setWinner(lolMatch.getAwayLolTeam());
             awayLolTeam.setWins(awayLolTeam.getWins() + 1);
             homeLolTeam.setLost(homeLolTeam.getLost() + 1);

         } else if (home>away){
             lolMatch.setWinner(lolMatch.getHomeLolTeam());
             homeLolTeam.setWins(homeLolTeam.getWins() + 1);
             awayLolTeam.setLost(awayLolTeam.getLost() + 1);
         }
            lolMatchRepository.save(lolMatch);
            lolTeamRepository.save(homeLolTeam);
            lolTeamRepository.save(awayLolTeam);
        }

        for (int i = 1; i <= 2; i++) {
            List<LolTeam> lolTeamList = new ArrayList<>();
            if (i==1){
                lolTeamList = lolTeamRepository.findAllByLolLeagueName("EU West");
            } else if (i==2) {
                lolTeamList = lolTeamRepository.findAllByLolLeagueName("EU East & Nordic");
            }
            lolTeamList.sort(comparing(LolTeam::getWins).reversed());

            int position = 0;
            for (LolTeam lolTeam : lolTeamList) {
                position++;
                lolTeam.setPosition(position);
                lolTeamRepository.save(lolTeam);
            }
        }
        System.out.println("Results are ready!" + LocalDateTime.now());
    }
}
