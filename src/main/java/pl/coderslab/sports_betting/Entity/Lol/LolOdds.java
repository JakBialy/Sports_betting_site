package pl.coderslab.sports_betting.Entity.Lol;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.Shared.Odds;

import javax.persistence.*;

/**
 * class for odds for League of Legends matches, which are generated in odd scheduled service
 * inheriting basic fields from Odds abstract class
 */
@Entity
@Table(name = "lolOdds")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public @Data
class LolOdds extends Odds {

    /**
     *  association with LolMatch connected with odds
     */
    @OneToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private LolMatch lolMatch;

    @Builder
    public LolOdds(Long id, String bookmaker, double oddHome, double oddAway, LolMatch lolMatch) {
        super(id, bookmaker, oddHome, oddAway);
        this.lolMatch = lolMatch;
    }
}
