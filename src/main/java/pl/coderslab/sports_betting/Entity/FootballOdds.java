package pl.coderslab.sports_betting.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "footballOdds")
public @Data
class FootballOdds {

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
