package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Football.FootballMatch;
import pl.coderslab.sports_betting.Repository.Fotball.FootballMatchRepository;
import pl.coderslab.sports_betting.Service.Football.Service.FootballMatchService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FootballMatchServiceImpl implements FootballMatchService {

    @Autowired
    FootballMatchRepository footballMatchRepository;

    public List<FootballMatch> allMatches(){
        List<FootballMatch> list = footballMatchRepository.findAll();
        return list;
    }

    public List<FootballMatch> plannedMatches(){
        List<FootballMatch> list = footballMatchRepository.findAllByStatus("planned");
        return list;
    }
    public List<FootballMatch> liveMatches(){
        List<FootballMatch> firstHalf = footballMatchRepository.findAllByStatus("First Half");
        List<FootballMatch> secondHalf = footballMatchRepository.findAllByStatus("Second Half");
        List <FootballMatch> inGame = new ArrayList<>();
        inGame.addAll(firstHalf);
        inGame.addAll(secondHalf);
        return inGame;
    }

    public List<FootballMatch> homeMatches(Long teamId){
        List<FootballMatch> list = footballMatchRepository.findAllByHomeFootballTeamIdOrderByStart(teamId);
        return list;
    }

    public List<FootballMatch> awayMatches(Long teamId){
        List<FootballMatch> list = footballMatchRepository.findAllByAwayFootballTeamIdOrderByStart(teamId);
        return list;
    }

    public FootballMatch findById(Long teamId){
        FootballMatch footballMatch = footballMatchRepository.getOne(teamId);
        return footballMatch;
    }

    public List<FootballMatch> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(localDateTime1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(localDateTime2, formatter);
        List<FootballMatch> list = footballMatchRepository.findAllByStartIsBetween(dateTime1,dateTime2);
        return list;
    }
}
