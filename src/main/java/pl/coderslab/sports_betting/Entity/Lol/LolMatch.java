package pl.coderslab.sports_betting.Entity.Lol;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lolMatches")
public @Data
class LolMatch {

    /**
     * FootballMatch is storing lol match parameters used
     * to monitoring matches, setting and calculating bets
     *
     * boolean checked is predefined as a false - after bet check (bet scheduled service)
     * parameter is changed into true
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime start;

    private LocalDateTime end;

    private String status;

    private int homeScore;

    private int awayScore;

    private boolean checked = false;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    @JsonBackReference
    private LolTeam winner;

    @ManyToOne
    @JoinColumn(name = "homeTeam_id")
    @JsonBackReference
    private LolTeam homeLolTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeam_id")
    @JsonBackReference
    private LolTeam awayLolTeam;

    @OneToOne(mappedBy="lolMatch")
    private LolOdds lolOdds;

    @OneToMany(mappedBy="lolMatch")
    // blocking list of bets for external API
    private List<LolBet> lolBetList = new ArrayList<>();

    @JsonIgnore
    public List<LolBet> getLolBetList() {
        return lolBetList;
    }

}
