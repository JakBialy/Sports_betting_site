package pl.coderslab.sports_betting.Service.Lol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolMatch;
import pl.coderslab.sports_betting.Repository.Lol.LolMatchRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LolMatchService {

    @Autowired
    LolMatchRepository lolMatchRepository;

    public List<LolMatch> allMatches(){
        List<LolMatch> list = lolMatchRepository.findAll();
        return list;
    }

    public List<LolMatch> plannedMatches(){
        List<LolMatch> list = lolMatchRepository.findAllByStatus("planned");
        return list;
    }
    public List<LolMatch> liveMatches(){
        List<LolMatch> live = lolMatchRepository.findAllByStatus("In game");
        return live;
    }

    public List<LolMatch> homeMatches(Long teamId){
        List<LolMatch> list = lolMatchRepository.findAllByHomeLolTeamIdOrderByStart(teamId);
        return list;
    }

    public List<LolMatch> awayMatches(Long teamId){
        List<LolMatch> list = lolMatchRepository.findAllByAwayLolTeamIdOrderByStart(teamId);
        return list;
    }

    public LolMatch findById(Long teamId){
        LolMatch lolMatch = lolMatchRepository.getOne(teamId);
        return lolMatch;
    }

    public List<LolMatch> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(localDateTime1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(localDateTime2, formatter);
        List<LolMatch> list = lolMatchRepository.findAllByStartIsBetween(dateTime1,dateTime2);
        return list;
    }
}
