package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "footballMatches")
public @Data
class FootballMatch {

    /**
     * FootballMatch is storing football match parameters used
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

    private int homeHalfScore;

    private int awayScore;

    private int awayHalfScore;

    private boolean checked = false;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    @JsonBackReference
    private FootballTeam winner;

    @ManyToOne
    @JoinColumn(name = "homeTeam_id")
    @JsonBackReference
    private FootballTeam homeFootballTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeam_id")
    @JsonBackReference
    private FootballTeam awayFootballTeam;

    @OneToOne(mappedBy="footballMatch")
    private FootballOdds footballOdds;

    @OneToMany(mappedBy="footballMatch")
    // blocking list of bets for external API
    private List<FootballBet> footballBetList = new ArrayList<>();

    @JsonIgnore
    public List<FootballBet> getFootballBetList() {
        return footballBetList;
    }
}
