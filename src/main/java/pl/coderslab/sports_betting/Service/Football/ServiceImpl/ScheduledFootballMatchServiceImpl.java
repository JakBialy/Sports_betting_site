package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Football.FootballMatch;
import pl.coderslab.sports_betting.Entity.Football.FootballTeam;
import pl.coderslab.sports_betting.Repository.Fotball.FootballMatchRepository;
import pl.coderslab.sports_betting.Repository.Fotball.FootballTeamRepository;
import pl.coderslab.sports_betting.Service.Football.Service.ScheduledFootballMatchService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Comparator.comparing;

@Service
public class ScheduledFootballMatchServiceImpl implements ScheduledFootballMatchService {

    @Autowired
    FootballTeamRepository footballTeamRepository;

    @Autowired
    FootballMatchRepository footballMatchRepository;

    private static int footballStaticCounter = 0;

    @Scheduled(cron = ("0 0/5 * 1/1 * ?"))
    public void startMatches() {
        List<FootballMatch> footballMatchList = new ArrayList<>();
        List<FootballTeam> footballTeamList = new ArrayList<>();
        int counter = 0;

        for (int i = 1; i <= 2; i++) {
            if (i == 1) {
                footballTeamList = footballTeamRepository.findAllByFootballLeagueName("Somewhership");
                Collections.shuffle(footballTeamList);

            } else {
                footballTeamList = footballTeamRepository.findAllByFootballLeagueName("Randomship");
                Collections.shuffle(footballTeamList);
                counter = 0;
            }
            for (int j = 1; j <= 5; j++) {
                // shuffle teams to make them random every time
                FootballMatch footballMatch = new FootballMatch();
                footballMatch.setStart(LocalDateTime.now().plusMinutes(3));
                footballMatch.setEnd(LocalDateTime.now().plusMinutes(5));
                footballMatch.setHomeFootballTeam(footballTeamList.get(counter));
                footballMatch.setAwayFootballTeam(footballTeamList.get(1 + counter));
                footballMatch.setStatus("planned");
                footballMatchList.add(footballMatch);
                counter += 2;
            }
        }
        footballMatchRepository.saveAll(footballMatchList);
        System.out.println("Another set of matches going on! " + LocalDateTime.now().toString());
    }

    @Scheduled(cron = ("58 3,4,8,9,13,14,18,19,23,24,28,29,33,34,38,39,43,44,48,49,53,54,58,59 * * * ?"))
    public void goalsMaker() {
        List<FootballMatch> list = footballMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        if (!(list.isEmpty())){
            footballStaticCounter++;
        }

        for (FootballMatch footballMatch : list) {
            Random r = new Random();
            int goalAway = r.nextInt(3);
            int goalHome = r.nextInt(3);

            if (!(footballStaticCounter % 2 == 0)) {
                footballMatch.setAwayHalfScore(footballMatch.getAwayHalfScore() + goalAway);
                footballMatch.setHomeHalfScore(footballMatch.getHomeHalfScore() + goalHome);
                footballMatch.setAwayScore(footballMatch.getAwayHalfScore());
                footballMatch.setHomeScore(footballMatch.getHomeHalfScore());
                footballMatch.setStatus("Second Half");
            } else if ((footballStaticCounter % 2 == 0)) {
                footballMatch.setAwayScore(footballMatch.getAwayScore() + goalAway);
                footballMatch.setHomeScore(footballMatch.getHomeScore() + goalHome);
                footballMatch.setStatus("Full Time");
            }
            footballMatchRepository.save(footballMatch);
        }
        System.out.println("Another set of goals going on! " + LocalDateTime.now().toString());
    }

    @Scheduled(cron = ("0 3,8,13,18,23,28,33,38,43,48,53,58 * * * ?"))
    public void matchStartStatus() {
        List<FootballMatch> footballMatchList1 = footballMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        for (FootballMatch load : footballMatchList1) {
            load.setStatus("First Half");
            footballMatchRepository.save(load);
        }
        System.out.println("FootballMatch started!" + LocalDateTime.now().toString());
    }

    @Scheduled(cron = ("59 4,9,14,19,24,29,34,39,44,49,54,59 * * * ?"))
    public void matchesResult() {
        List<FootballMatch> list = footballMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());

        for (FootballMatch footballMatch : list) {
         int away = footballMatch.getAwayScore();
         int home = footballMatch.getHomeScore();
         FootballTeam awayFootballTeam = footballMatch.getAwayFootballTeam();
         FootballTeam homeFootballTeam = footballMatch.getHomeFootballTeam();

         if (away>home){
             footballMatch.setWinner(footballMatch.getAwayFootballTeam());
             awayFootballTeam.setWins(awayFootballTeam.getWins() + 1);
             homeFootballTeam.setLost(homeFootballTeam.getLost() + 1);

         } else if (home>away){
             footballMatch.setWinner(footballMatch.getHomeFootballTeam());
             homeFootballTeam.setWins(homeFootballTeam.getWins() + 1);
             awayFootballTeam.setLost(awayFootballTeam.getLost() + 1);
         } else {
             awayFootballTeam.setDraws(awayFootballTeam.getDraws() + 1);
             homeFootballTeam.setDraws(homeFootballTeam.getDraws() + 1);
         }
            footballMatchRepository.save(footballMatch);
            footballTeamRepository.save(homeFootballTeam);
            footballTeamRepository.save(awayFootballTeam);
        }

        for (int i = 1; i <= 2; i++) {
            List<FootballTeam> footballTeamList = new ArrayList<>();
            if (i==1){
                footballTeamList = footballTeamRepository.findAllByFootballLeagueName("Somewhership");
            } else if (i==2) {
                footballTeamList = footballTeamRepository.findAllByFootballLeagueName("Randomship");
            }
            footballTeamList.sort(comparing(FootballTeam::getWins).reversed());

            int position = 0;
            for (FootballTeam footballTeam : footballTeamList) {
                position++;
                footballTeam.setPosition(position);
                footballTeamRepository.save(footballTeam);
            }
        }
        System.out.println("Results are ready!" + LocalDateTime.now());
    }
}
