package pl.coderslab.sports_betting.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Match;
import pl.coderslab.sports_betting.Entity.Team;
import pl.coderslab.sports_betting.Repository.MatchRepository;
import pl.coderslab.sports_betting.Repository.TeamRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MatchRepository matchRepository;


    public void populateDb(){
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
    }
}
