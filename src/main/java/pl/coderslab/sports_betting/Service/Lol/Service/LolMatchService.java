package pl.coderslab.sports_betting.Service.Lol.Service;

import pl.coderslab.sports_betting.Entity.Lol.LolMatch;

import java.util.List;

public interface LolMatchService {

    public List<LolMatch> allMatches();

    public List<LolMatch> plannedMatches();

    public List<LolMatch> liveMatches();

    public List<LolMatch> homeMatches(Long teamId);

    public List<LolMatch> awayMatches(Long teamId);

    public LolMatch findById(Long teamId);

    public List<LolMatch> findMatchesWhereStartIsBetween(String localDateTime1, String localDateTime2);

    }
