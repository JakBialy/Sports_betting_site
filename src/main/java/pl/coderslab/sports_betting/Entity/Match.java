package pl.coderslab.sports_betting.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "awayTeam_id")
    Team awayTeam;

    @OneToOne(mappedBy="match")
    private Odds odds;
}
