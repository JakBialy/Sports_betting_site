package pl.coderslab.sports_betting.Entity.Lol;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "lolOdds")
public @Data
class LolOdds {

    /**
     * Lol Odds are storing data about odds
     * created by random generator (odd scheduled service)
     * Lol Odds are connected with LolMatch (to have access to actual odds)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String bookmaker;

    private Double oddHome;

    private Double oddAway;

    @OneToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private LolMatch lolMatch;
}
