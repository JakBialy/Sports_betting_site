package pl.coderslab.sports_betting.Service.Lol.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Lol.LolMatch;
import pl.coderslab.sports_betting.Repository.Lol.LolMatchRepository;
import pl.coderslab.sports_betting.Service.Lol.Service.LolMatchService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LolMatchServiceImpl implements LolMatchService {
    private final LolMatchRepository lolMatchRepository;

    @Autowired
    public LolMatchServiceImpl(LolMatchRepository lolMatchRepository) {
        this.lolMatchRepository = lolMatchRepository;
    }

    /**
     * Method is looking for all lol matches
     * @return list of all lol matches
     */
    public List<LolMatch> allMatches(){
        return lolMatchRepository.findAll();
    }

    /**
     * Method is looking for all planned matches
     * @return list of all planned matches
     */
    public List<LolMatch> plannedMatches(){
        return lolMatchRepository.findAllByStatus("planned");
    }
    /**
     * Method is looking for all live matches
     * @return list of actually played games
     */
    public List<LolMatch> liveMatches(){
        return lolMatchRepository.findAllByStatus("In game");
    }

    /**
     * Method is looking for all home matches of selected team Id
     * @param teamId Id of lol team
     * @return list of all team home matches
     */
    public List<LolMatch> homeMatches(Long teamId){
        return lolMatchRepository.findAllByHomeLolTeamIdOrderByStart(teamId);
    }

    /**
     * Method is looking for all away matches of selected team Id
     * @param teamId Id of lol team
     * @return list of all team away matches
     */
    public List<LolMatch> awayMatches(Long teamId){
        return lolMatchRepository.findAllByAwayLolTeamIdOrderByStart(teamId);
    }

    /**
     * Looking for match by id
     * @param matchId selected match Id
     * @return single lol match
     */
    public LolMatch findById(Long matchId){
        return lolMatchRepository.getOne(matchId);
    }

    /**
     * Method is looking for match which started between some date specified by LocalDateTime
     * @param localDateTime1 starting point of looking for match
     * @param localDateTime2 ending point of looking for match
     * @return list of match which are between specfied date
     */
    public List<LolMatch> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime1 = LocalDateTime.parse(localDateTime1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(localDateTime2, formatter);
        return lolMatchRepository.findAllByStartIsBetween(dateTime1,dateTime2);
    }
}
