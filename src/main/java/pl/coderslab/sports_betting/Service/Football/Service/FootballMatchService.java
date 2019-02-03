package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballMatch;

import java.util.List;

public interface FootballMatchService {
     List<FootballMatch> allMatches();
     List<FootballMatch> plannedMatches();
     List<FootballMatch> liveMatches();
     List<FootballMatch> homeMatches(Long teamId);
     List<FootballMatch> awayMatches(Long teamId);
     FootballMatch findById(Long teamId);
     List<FootballMatch> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2);

    }
