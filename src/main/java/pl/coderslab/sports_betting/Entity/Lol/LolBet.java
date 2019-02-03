package pl.coderslab.sports_betting.Entity.Lol;

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
@Table(name = "lolBets")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public @Data
class LolBet extends Bet {

    /**
     * LolBet is a class for saving League of Legends bet parameter, common variables are inherited
     * from Bet abstract class, class LolBet is connected with LolMatch, User and LolTeam
     */

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    LolMatch lolMatch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    LolTeam lolTeam;

    // this kind of builder works just fine, but can't wait for stable super builder from lombok
    @Builder
    public LolBet(Long id, String type, BigDecimal money, LocalDateTime date, Boolean winner, BigDecimal odd,
                  float percentage, Boolean accepted, Boolean groupBet, LolMatch lolMatch, User user, LolTeam lolTeam) {
        super(id, type, money, date, winner, odd, groupBet, percentage, accepted);
        this.lolMatch = lolMatch;
        this.user = user;
        this.lolTeam = lolTeam;
    }
}
