package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.Shared.Team;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  FootballTeam is a class responsible for storing information about football team,
 *  inheriting abstract class Team
 */
@Entity
@Table(name = "footballTeams")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public @Data
class FootballTeam extends Team {
    /**
     * number of team draws
     */
    private int draws;

    /**
     * Team league
     */
    @ManyToOne
    @JoinColumn(name = "league_id")
    @JsonBackReference
    private FootballLeague footballLeague;

    /**
     * List of team home games
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="homeFootballTeam")
    private List<FootballMatch> homeTeamGames = new ArrayList<>();

    /**
     * List of team away games
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy="awayFootballTeam")
    private List<FootballMatch> awayTeamGames = new ArrayList<>();

    /**
     * List of team won games
     */
    @OneToMany(mappedBy="winner")
    private List<FootballMatch> wonGames = new ArrayList<>();

    /**
     * List of bets placed on this team
     */
    @OneToMany(mappedBy="footballTeam")
    private List<FootballBet> footballBets = new ArrayList<>();

    /**
     * visible for setting option to not sending bets via API
     * @return list of football bets
     */
    @JsonIgnore
    public List<FootballBet> getFootballBets() {
        return footballBets;
    }

    @Builder
    public FootballTeam(Long id, String team, int position, int wins, int lost, double winLostRatio, int draws,
                        FootballLeague footballLeague, List<FootballMatch> homeTeamGames, List<FootballMatch> awayTeamGames,
                        List<FootballMatch> wonGames, List<FootballBet> footballBets) {
        super(id, team, position, wins, lost, winLostRatio);
        this.draws = draws;
        this.footballLeague = footballLeague;
        this.homeTeamGames = homeTeamGames;
        this.awayTeamGames = awayTeamGames;
        this.wonGames = wonGames;
        this.footballBets = footballBets;
    }
}
