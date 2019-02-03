package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolMatch;

import java.util.List;

public interface LolMatchService {
     List<LolMatch> allMatches();
     List<LolMatch> plannedMatches();
     List<LolMatch> liveMatches();
     List<LolMatch> homeMatches(Long teamId);
     List<LolMatch> awayMatches(Long teamId);
     LolMatch findById(Long teamId);
     List<LolMatch> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2);
}

