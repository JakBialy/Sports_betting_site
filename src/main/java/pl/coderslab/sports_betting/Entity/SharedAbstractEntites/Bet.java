package pl.coderslab.sports_betting.Entity.SharedAbstractEntites;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Abstract class collecting common data for all type of bets
 */

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public @Data
abstract class Bet{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * type of bet (win, draw, lost)
     */
    @NotEmpty
    private  String type;

    /**
     * store amount of money placed
     */
    @Min(0)
    @NotNull
    private BigDecimal money;

    /**
     * time when bet was made
     */
    private LocalDateTime date;

    /**
     * boolean variable which is set to true/false after bet results(in scheduled bet service)
     */
    private Boolean winner;

    /**
     * stores odd ratio for specific bet(based on bet type)
     */
    private BigDecimal odd;

    /**
     * group bets are bets placed with a friend
     */
    private Boolean groupBet = false;

    /**
     * percentage of participation in bet (if groupBet)
     */
    // TODO name should be refactored (and checked with front-end)
    private float percentage;

    /**
     * is bet accepted
     */
    private Boolean accepted = true;


    /**
     * User which make a bet
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;
}
