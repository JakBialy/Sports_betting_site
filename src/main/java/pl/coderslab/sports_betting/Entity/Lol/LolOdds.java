package pl.coderslab.sports_betting.Entity.Lol;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "lolOdds")
public @Data
class LolOdds {

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
