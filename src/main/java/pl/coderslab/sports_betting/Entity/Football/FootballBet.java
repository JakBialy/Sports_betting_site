package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.Shared.Bet;
import pl.coderslab.sports_betting.Entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "footballBets")
@NoArgsConstructor
public @Data
class FootballBet extends Bet {

    /**
     * FootballBet is a class for saving football bet parameter, common variables are inherited
     * from Bet abstract class, class FootballBet is connected with FootballMatch, User, User(extra) and FootballTeam
     */

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    FootballMatch footballMatch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    /**
     *  extra - for group betting, user which was added by initial one
     */
    @ManyToOne
    @JoinColumn(name = "extra_id")
    @JsonBackReference
    User extra;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    FootballTeam footballTeam;

    // this kind of builder works just fine, but can't wait for stable super builder from lombok
    @Builder
    public FootballBet(Long id, String type, BigDecimal money, LocalDateTime date,
                       Boolean winner, BigDecimal odd, Boolean groupBet, float percentage, Boolean accepted,
                       FootballMatch footballMatch, User user, User extra, FootballTeam footballTeam) {
        super(id, type, money, date, winner, odd, groupBet, percentage, accepted);
        this.footballMatch = footballMatch;
        this.user = user;
        this.extra = extra;
        this.footballTeam = footballTeam;
    }
}
