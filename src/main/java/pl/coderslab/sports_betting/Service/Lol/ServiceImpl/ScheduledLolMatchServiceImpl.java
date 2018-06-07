package pl.coderslab.sports_betting.Service.Lol.ServiceImpl;

import org.decimal4j.util.DoubleRounder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.sports_betting.Entity.Lol.LolMatch;
import pl.coderslab.sports_betting.Entity.Lol.LolTeam;
import pl.coderslab.sports_betting.Repository.Lol.LolMatchRepository;
import pl.coderslab.sports_betting.Repository.Lol.LolTeamRepository;
import pl.coderslab.sports_betting.Service.Lol.Service.ScheduledLolMatchService;

import javax.persistence.FetchType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static java.util.Comparator.comparing;

@Service
public class ScheduledLolMatchServiceImpl implements ScheduledLolMatchService {

    @Autowired
    LolTeamRepository lolTeamRepository;

    @Autowired
    LolMatchRepository lolMatchRepository;

    private static int LolStaticCounter = 0;

    /**
     * Method is populating database with set of matches every 5 full minutes
     * First loop is going two times for each Lol league, before going to
     * nested loop teams are shuffle to make their games random
     * Every time nested loop is generating five matches
     * matches starts 3 minutes from actual time and will finish 5 time from actual time
     * each of team has one home match and one away match, they are selected
     * to matches by counter to make sure that everyone will play then
     * match status is set  as a "planned", 2 is added to counter to make next loop getting appropriate teams
     * finally matches are saved into db
     */
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

    /**
     * The method works in the end of every minute (58 sec), so two in total game "life" first is looking
     * for matches which didn't finished yet, if list is not empty, then counter is increased
     * for all of the matches are generated two random numbers from 0 to 2
     * method is adding actual score to new goals
     * finally method save matches to database
     */

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

    /**
     * Every 3 full minutes matches which end is in the future have
     * set status to "first half"
     * it is saving them to db
     */
    @Scheduled(cron = ("0 3,8,13,18,23,28,33,38,43,48,53,58 * * * ?"))
    public void matchStartStatus() {
        List<LolMatch> lolMatchList1 = lolMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());
        for (LolMatch load : lolMatchList1) {
            load.setStatus("In game");
            lolMatchRepository.save(load);
        }
        System.out.println("LolMatch started!" + LocalDateTime.now().toString());
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
     * also draws and lost in case of having same wins) then setting changes of leagues into database
     *
     */
    // todo some changes here!

    @Scheduled(cron = ("59 4,9,14,19,24,29,34,39,44,49,54,59 * * * ?"))
    public void matchesResult() {
        List<LolMatch> list = lolMatchRepository.findAllByEndIsGreaterThan(LocalDateTime.now());

        for (LolMatch lolMatch : list) {
         int away = lolMatch.getAwayScore();
         int home = lolMatch.getHomeScore();
         LolTeam awayLolTeam = lolMatch.getAwayLolTeam();
         LolTeam homeLolTeam = lolMatch.getHomeLolTeam();

            scoreMaker(lolMatch, away, home, awayLolTeam, homeLolTeam);
            ratioWinLost(awayLolTeam, homeLolTeam);

            lolMatchRepository.save(lolMatch);
            lolTeamRepository.save(homeLolTeam);
            lolTeamRepository.save(awayLolTeam);
        }

        teamPositioning();
        System.out.println("Results are ready!" + LocalDateTime.now());
    }


    private void ratioWinLost(LolTeam awayLoLTeam, LolTeam homeLoLTeam) {
        int homeMatchesTotal = homeLoLTeam.getAwayTeamGames().size() + homeLoLTeam.getHomeTeamGames().size();
        int awayMatchesTotal = awayLoLTeam.getHomeTeamGames().size() + awayLoLTeam.getAwayTeamGames().size();
        int winsAway = awayLoLTeam.getWins();
        int winsHome = homeLoLTeam.getWins();

        Double ratioHome = (double)winsHome/(double)homeMatchesTotal * 100;
        homeLoLTeam.setWinLostRatio(DoubleRounder.round(ratioHome,2));

        Double ratioAway = (double)winsAway/(double)awayMatchesTotal * 100;
        awayLoLTeam.setWinLostRatio(DoubleRounder.round(ratioAway,2));
    }

    private void teamPositioning() {
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
    }

    private void scoreMaker(LolMatch lolMatch, int away, int home, LolTeam awayLolTeam, LolTeam homeLolTeam) {
        if (away>home){
            lolMatch.setWinner(lolMatch.getAwayLolTeam());
            awayLolTeam.setWins(awayLolTeam.getWins() + 1);
            homeLolTeam.setLost(homeLolTeam.getLost() + 1);

        } else if (home>away){
            lolMatch.setWinner(lolMatch.getHomeLolTeam());
            homeLolTeam.setWins(homeLolTeam.getWins() + 1);
            awayLolTeam.setLost(awayLolTeam.getLost() + 1);
        }
    }
}
