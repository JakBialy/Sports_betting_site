package pl.coderslab.sports_betting.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Match;
import pl.coderslab.sports_betting.Repository.MatchRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    public List<Match> allMatches(){
        List<Match> list = matchRepository.findAll();
        return list;
    }

    public List<Match> plannedMatches(){
        List<Match> list = matchRepository.findAllByStatus("planned");
        return list;
    }
    public List<Match> liveMatches(){
        List<Match> firstHalf = matchRepository.findAllByStatus("First Half");
        List<Match> secondHalf = matchRepository.findAllByStatus("Second Half");
        List <Match> inGame = new ArrayList<>();
        inGame.addAll(firstHalf);
        inGame.addAll(secondHalf);
        return inGame;
    }

    public List<Match> homeMatches(Long teamId){
        List<Match> list = matchRepository.findAllByHomeTeamIdOrderByStart(teamId);
        return list;
    }

    public List<Match> awayMatches(Long teamId){
        List<Match> list = matchRepository.findAllByAwayTeamIdOrderByStart(teamId);
        return list;
    }

    public Match findById(Long teamId){
        Match match = matchRepository.getOne(teamId);
        return match;
    }

    public List<Match> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(localDateTime1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(localDateTime2, formatter);
        List<Match> list = matchRepository.findAllByStartIsBetween(dateTime1,dateTime2);
        return list;
    }
}
