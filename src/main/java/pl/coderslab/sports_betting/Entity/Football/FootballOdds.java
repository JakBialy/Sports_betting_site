package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.SharedAbstractEntites.Odds;

import javax.persistence.*;

/**
 * class for odds for football matches, which are generated in odd scheduled service
 * inheriting basic fields from Odds abstract class
 */
@Entity
@Table(name = "footballOdds")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public @Data
class FootballOdds extends Odds {

    /**
     * odds for betting on home team for half time of a game
     */
    private double oddHomeHalf;

    /**
     * odds for betting on away team for half time of a game
     */
    private double oddAwayHalf;

    /**
     * odds for draw
     */
    private double oddX;

    /**
     * association with football match connected with odds
     */
    @OneToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    private FootballMatch footballMatch;

    @Builder
    public FootballOdds(Long id, String bookmaker, double oddHome, double oddAway, double oddHomeHalf, double oddAwayHalf, double oddX, FootballMatch footballMatch) {
        super(id, bookmaker, oddHome, oddAway);
        this.oddHomeHalf = oddHomeHalf;
        this.oddAwayHalf = oddAwayHalf;
        this.oddX = oddX;
        this.footballMatch = footballMatch;
    }
}
