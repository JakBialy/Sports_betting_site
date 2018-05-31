package pl.coderslab.sports_betting.Service.Scheduled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Match;
import pl.coderslab.sports_betting.Entity.Team;
import pl.coderslab.sports_betting.Repository.MatchRepository;
import pl.coderslab.sports_betting.Repository.TeamRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class MatchService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;

    static int counter = 0;

    @Scheduled(cron = ("0 0/5 * 1/1 * ?"))
    public void startMatches(){
        List<Match> matchList = new ArrayList<>();
        List<Team> teamList = new ArrayList<>();
        int counter = 0;

        for (int i = 1; i <=2; i++) {
            if (i==1){
                teamList = teamRepository.findAllByLeague_Name("Somewhership");
                Collections.shuffle(teamList);

            } else{
                teamList = teamRepository.findAllByLeague_Name("Randomship");
                Collections.shuffle(teamList);
                counter = 0;
            }
            for (int j = 1; j <=5 ; j++) {
                // shuffle teams to make them random every time
                Match match = new Match();
                match.setStart(LocalDateTime.now().plusMinutes(3));
                match.setEnd(LocalDateTime.now().plusMinutes(5));
                match.setHomeTeam(teamList.get(counter));
                match.setAwayTeam(teamList.get(1 + counter));
                match.setStatus("planned");
                matchList.add(match);
                counter += 2;
            }
        }
        matchRepository.saveAll(matchList);
        System.out.println("Another set of matches going on! " + LocalDateTime.now().toString());
    }

    @Scheduled(cron = ("59 3,4,8,9,13,14,18,19,23,24,28,29,33,34,38,39,43,44,48,49,53,54,58,59 * * * ?"))
    public void goalsMaker(){
        List<Match> list = matchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        counter++;

        for (Match match: list) {
            Random r = new Random();
            int goalAway = 0;
            int goalHome = 0;

            switch ((r.nextInt(3) + 1)) {
                case 1:
                    goalAway = 0;
                    break;
                case 2:
                    goalAway = 1;
                    break;
                case 3:
                    goalAway = 2;
            }

            switch ((r.nextInt(3) + 1)) {
                case 1:
                    goalHome = 2;
                    break;
                case 2:
                    goalHome = 1;
                    break;
                case 3:
                    goalHome = 0;
            }

            if (!(counter % 2 == 0)){
                match.setAwayHalfScore(match.getAwayHalfScore() + goalAway);
                match.setHomeHalfScore(match.getHomeHalfScore() + goalHome);
                match.setAwayScore(match.getAwayHalfScore());
                match.setHomeScore(match.getHomeHalfScore());
                match.setStatus("Second Half");
            }  else if ((counter % 2 == 0)){
                match.setAwayScore(match.getAwayScore() + goalAway);
                match.setHomeScore(match.getHomeScore() + goalHome);
                match.setStatus("Full Time");
            }
            matchRepository.save(match);
        }
        System.out.println("Another set of goals going on! " + LocalDateTime.now().toString());
    }

    @Scheduled(cron = ("0 3,8,13,18,23,28,33,38,43,48,53,58 * * * ?"))
    public void matchStartStatus(){
        List<Match> matchList1 = matchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        for (Match load: matchList1) {
            load.setStatus("First Half");
            matchRepository.save(load);
        }
        System.out.println("Match started!" + LocalDateTime.now().toString());
    }

}
