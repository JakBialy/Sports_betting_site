package pl.coderslab.sports_betting.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "matches")
public @Data
class Match {

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
    @JoinColumn(name = "homeTeam_id")
    @JsonBackReference
    Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeam_id")
    @JsonBackReference
    Team awayTeam;

    @OneToOne(mappedBy="match")
    private Odds odds;

    @OneToMany(mappedBy="match")
    private List<Bet> betList = new ArrayList<>();
}
