package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "footballOdds")
public @Data
class FootballOdds {

    /**
     * Football Odds are storing data about odds
     * created by random generator (odd scheduled service)
     * Football Odds are connected with footballMatch (to have access to actual odds)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bookmaker;

    private Double oddHome;

    private Double oddHomeHalf;

    private Double oddX;

    private Double oddAway;

    private Double oddAwayHalf;

    @OneToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private FootballMatch footballMatch;
}
