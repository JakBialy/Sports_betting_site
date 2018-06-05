package pl.coderslab.sports_betting.Entity;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    LocalDateTime start;

    LocalDateTime end;

    String status;

    int homeScore;

    int homeHalfScore;

    int awayScore;

    int awayHalfScore;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    @JsonBackReference
    FootballTeam winner;

    @ManyToOne
    @JoinColumn(name = "homeTeam_id")
    @JsonBackReference
    FootballTeam homeFootballTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeam_id")
    @JsonBackReference
    FootballTeam awayFootballTeam;

    @OneToOne(mappedBy="footballMatch")
    private FootballOdds footballOdds;

    @OneToMany(mappedBy="footballMatch")
    // blocking list of bets for external API
    private List<Bet> betList = new ArrayList<>();

    @JsonIgnore
    public List<Bet> getBetList() {
        return betList;
    }
}
