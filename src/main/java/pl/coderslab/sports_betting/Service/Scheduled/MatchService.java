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

    @Scheduled(fixedDelay = 1000*60*10)
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
                match.setStart(LocalDateTime.now().plusMinutes(5));
                match.setEnd(LocalDateTime.now().plusMinutes(10));
                match.setHomeTeam(teamList.get(counter));
                match.setAwayTeam(teamList.get(1 + counter));
                matchList.add(match);
                counter += 2;
            }
        }
        matchRepository.saveAll(matchList);
        System.out.println("Another set of matches going on! " + LocalDateTime.now().toString());
    }

    @Scheduled(fixedDelay = 1000*60, initialDelay = 1000*60*6-500)
    public void goalsMaker(){
        List<Match> list = matchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        int counter = 0;

        for (Match match: list) {
            // wrong counter counting - wrong method
            counter ++;
            Random r = new Random();
            int goalAway = 0;
            int goalHome = 0;
            // if there is some number from random - 33% chance to have a goal
            // it could be any number from 1 to 3
            if (r.nextInt(3) + 1 == 3) {
                goalAway = 1;
            }
            if (r.nextInt(3) + 1 == 3){
                goalHome = 1;
            }

            if (counter <= 20){
                match.setAwayHalfScore(match.getAwayHalfScore() + goalAway);
                match.setHomeHalfScore(match.getHomeHalfScore() + goalHome);
                match.setAwayScore(match.getAwayHalfScore());
                match.setHomeScore(match.getHomeHalfScore());

            }  else if (counter > 30){
                match.setAwayScore(match.getAwayScore() + goalAway);
                match.setHomeScore(match.getHomeScore() + goalHome);
            }

            matchRepository.save(match);
            if (counter % 50 == 0){
                try {
                    TimeUnit.MINUTES.sleep(6);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Another set of goals going on! " + LocalDateTime.now().toString() + " " + counter);

    }


}
