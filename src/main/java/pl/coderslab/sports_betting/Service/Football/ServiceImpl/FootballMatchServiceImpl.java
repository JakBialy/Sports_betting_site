package pl.coderslab.sports_betting.Service.Football.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Football.FootballMatch;
import pl.coderslab.sports_betting.Repository.Football.FootballMatchRepository;
import pl.coderslab.sports_betting.Service.Football.Service.FootballMatchService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class FootballMatchServiceImpl implements FootballMatchService {

    @Autowired
    FootballMatchRepository footballMatchRepository;

    /**
     * Method is looking for all football matches
     * @return list of all football matches
     */
    public List<FootballMatch> allMatches(){
        List<FootballMatch> list = footballMatchRepository.findAll();
        return list;
    }

    /**
     * Method is looking for all planned matches
     * @return list of all planned matches
     */
    public List<FootballMatch> plannedMatches(){
        List<FootballMatch> list = footballMatchRepository.findAllByStatus("planned");
        return list;
    }

    /**
     * Method is looking for all live matches both in first half and second half phase
     * @return list of actually played games
     */
    public List<FootballMatch> liveMatches(){
        List<FootballMatch> firstHalf = footballMatchRepository.findAllByStatus("First Half");
        List<FootballMatch> secondHalf = footballMatchRepository.findAllByStatus("Second Half");
        List <FootballMatch> inGame = new ArrayList<>();
        inGame.addAll(firstHalf);
        inGame.addAll(secondHalf);
        return inGame;
    }

    /**
     * Method is looking for all home matches of selected team Id
     * @param teamId Id of football team
     * @return list of all team home matches
     */
    public List<FootballMatch> homeMatches(Long teamId){
        List<FootballMatch> list = footballMatchRepository.findAllByHomeFootballTeamIdOrderByStart(teamId);
        return list;
    }

    /**
     * Method is looking for all away matches of selected team Id
     * @param teamId Id of football team
     * @return list of all team away matches
     */
    public List<FootballMatch> awayMatches(Long teamId){
        List<FootballMatch> list = footballMatchRepository.findAllByAwayFootballTeamIdOrderByStart(teamId);
        return list;
    }

    /**
     * Looking for match by id
     * @param matchId selected match Id
     * @return single football match
     */
    public FootballMatch findById(Long matchId){
        FootballMatch footballMatch = footballMatchRepository.getOne(matchId);
        return footballMatch;
    }

    /**
     * Method is looking for match which started between some date specified by LocalDateTime
     * @param localDateTime1 starting point of looking for match
     * @param localDateTime2 ending point of looking for match
     * @return list of match which are between specfied date
     */

    public List<FootballMatch> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(localDateTime1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(localDateTime2, formatter);
        List<FootballMatch> list = footballMatchRepository.findAllByStartIsBetween(dateTime1,dateTime2);
        return list;
    }
}
