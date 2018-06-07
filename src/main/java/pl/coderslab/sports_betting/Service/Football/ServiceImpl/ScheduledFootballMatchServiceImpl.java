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

    /**
     * Method is populating database with set of matches every 5 full minutes
     * First loop is going two times for each Football league, before going to
     * nested loop teams are shuffle to make their games random
     * Every time nested loop is generating five matches
     * matches starts 3 minutes from actual time and will finish 5 time from actual time
     * each of team has one home match and one away match, they are selected
     * to matches by counter to make sure that everyone will play then
     * match status is set  as a "planned", 2 is added to counter to make next loop getting apropiate teams
     * finally matches are saved into db
     */
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

    /**
     * The method works in the end of every minute (58 sec), so two in total game "life" first is looking
     * for matches which didn't finished yet, if list is not empty, then counter is increased
     * for all of the matches are generated two random numbers from 0 to 2
     * then if counter is odd number goals are added to half score and scores and status is set to "Second Half"
     * if counter is even number then goals are added only to score and status is set to "Full Time"
     * in both cases method is adding actual score to new goals
     * finally method save matches to database
     */
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

    /**
     * Every 3 full minutes matches which end is in the future have
     * set status to "first half"
     * it is saving them to db
     */
    @Scheduled(cron = ("0 3,8,13,18,23,28,33,38,43,48,53,58 * * * ?"))
    public void matchStartStatus() {
        List<FootballMatch> footballMatchList1 = footballMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        for (FootballMatch load : footballMatchList1) {
            load.setStatus("First Half");
            footballMatchRepository.save(load);
        }
        System.out.println("FootballMatch started!" + LocalDateTime.now().toString());
    }

    /**
     * Method is running in the end of match  - every one seconds before full 5 minutes method
     * method is getting list of all unfinished matches where end is in the future
     * for every match is getting teams
     * if away home has more goals than home team then is setting them 1 win and to away team 1 lost
     * otherwise in reversed case
     * in case of draw (else) method is adding draw to every team
     * all matches and teams are saved into db
     *
     * after this loop is looking for two leagues and setting
     * position of teams, comparing them wins (it would be nice to change it later to compare
     * alse draws and losts in case of having same wins) then setting changes of leagues into datbase
     *
     */
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

        positioning();
        System.out.println("Results are ready!" + LocalDateTime.now());
    }

    private void positioning() {
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
    }
}
