package pl.coderslab.sports_betting.Service.Football.Service;

import pl.coderslab.sports_betting.Entity.Football.FootballMatch;

import java.util.List;

public interface FootballMatchService {

    public List<FootballMatch> allMatches();

    public List<FootballMatch> plannedMatches();

    public List<FootballMatch> liveMatches();

    public List<FootballMatch> homeMatches(Long teamId);

    public List<FootballMatch> awayMatches(Long teamId);

    public FootballMatch findById(Long teamId);

    public List<FootballMatch> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2);

    }
