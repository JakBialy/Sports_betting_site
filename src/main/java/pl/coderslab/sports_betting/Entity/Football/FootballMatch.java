package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.Shared.Match;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * FootballMatch is storing football match parameters used
 * to monitoring matches, setting and calculating bets
 * inheriting common variables from Match class
 */
@Entity
@Table(name = "footballMatches")
public @Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
class FootballMatch extends Match {

    /**
     * score of home team for half-time
     */
    private int homeHalfScore;

    /**
     * score of away team for half-time
     */
    private int awayHalfScore;

    /**
     * team which won match
     */
    @ManyToOne
    @JoinColumn(name = "winner_id")
    @JsonBackReference
    private FootballTeam winner;

    /**
     * home team
     */
    @ManyToOne
    @JoinColumn(name = "homeTeam_id")
    @JsonBackReference
    private FootballTeam homeFootballTeam;

    /**
     * away team
     */
    @ManyToOne
    @JoinColumn(name = "awayTeam_id")
    @JsonBackReference
    private FootballTeam awayFootballTeam;

    /**
     * odds generated for this match
     */
    @OneToOne(mappedBy="footballMatch")
    private FootballOdds footballOdds;

    /**
     * list of bets set for this match
     */
    @OneToMany(mappedBy="footballMatch")
    private List<FootballBet> footballBetList = new ArrayList<>();

    /**
     * showed here just for blocking list of bets for external API
     * @return list of football bets
     */
    @JsonIgnore
    public List<FootballBet> getFootballBetList() {
        return footballBetList;
    }
}
