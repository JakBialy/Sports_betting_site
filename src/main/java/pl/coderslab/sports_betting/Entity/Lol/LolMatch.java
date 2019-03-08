package pl.coderslab.sports_betting.Entity.Lol;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.SharedAbstractEntites.Match;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * LolMatch is storing football match parameters used
 * to monitoring matches, setting and calculating bets,
 * inheriting common variables from Match class
 */
@Entity
@Table(name = "lolMatches")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public @Data
class LolMatch extends Match {

    /**
     * team which won match
     */
    @ManyToOne
    @JoinColumn(name = "winner_id")
    @JsonBackReference
    private LolTeam winner;

    /**
     * home team
     */
    @ManyToOne
    @JoinColumn(name = "homeTeam_id")
    @JsonBackReference
    private LolTeam homeLolTeam;

    /**
     * away team
     */
    @ManyToOne
    @JoinColumn(name = "awayTeam_id")
    @JsonBackReference
    private LolTeam awayLolTeam;

    /**
     * odds generated for this match
     */
    @OneToOne(mappedBy="lolMatch")
    private LolOdds lolOdds;

    /**
     * list of bets set for this match
     */
    @OneToMany(mappedBy="lolMatch")
    private List<LolBet> lolBetList = new ArrayList<>();

    /**
     * showed here just for blocking list of bets for external API
     * @return list of lol bets
     */
    @JsonIgnore
    public List<LolBet> getLolBetList() {
        return lolBetList;
    }

}
