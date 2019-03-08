package pl.coderslab.sports_betting.Entity.Lol;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.SharedAbstractEntites.Team;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  LolTeam is a class responsible for storing information about League of Legends team,
 *  inheriting abstract class Team
 */
@Entity
@Table(name = "lolTeams")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public @Data
class LolTeam extends Team {
    /**
     * Team league
     */
    @ManyToOne
    @JoinColumn(name = "league_id")
    @JsonBackReference
    private LolLeague lolLeague;

    /**
     * List of team home games
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="homeLolTeam")
    private List<LolMatch> homeTeamGames = new ArrayList<>();

    /**
     * List of team away games
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="awayLolTeam")
    private List<LolMatch> awayTeamGames = new ArrayList<>();

    /**
     * List of team won games
     */
    @OneToMany(mappedBy="winner")
    private List<LolMatch> wonGames = new ArrayList<>();

    /**
     * List of bets placed on this team
     */
    @OneToMany(mappedBy="lolTeam")
    private List<LolBet> lolBets = new ArrayList<>();

    /**
     * visible for setting option to not sending bets via API
     * @return list of lol bets
     */
    @JsonIgnore
    public List<LolBet> getLolBets() {
        return lolBets;
    }

// can be a problem for swagger
//    @ManyToMany(mappedBy="footballTeams")
//    private List<User> users = new ArrayList<>();

    @Builder
    public LolTeam(Long id, String team, int position, int wins, int lost, double winLostRatio, LolLeague lolLeague,
                   List<LolMatch> homeTeamGames, List<LolMatch> awayTeamGames, List<LolMatch> wonGames, List<LolBet> lolBets) {
        super(id, team, position, wins, lost, winLostRatio);
        this.lolLeague = lolLeague;
        this.homeTeamGames = homeTeamGames;
        this.awayTeamGames = awayTeamGames;
        this.wonGames = wonGames;
        this.lolBets = lolBets;
    }
}
