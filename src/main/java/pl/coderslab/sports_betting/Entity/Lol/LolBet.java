package pl.coderslab.sports_betting.Entity.Lol;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import pl.coderslab.sports_betting.Entity.User;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lolBets")
public @Data
class LolBet {

    /**
     * LolBet is a class for saving bet parameter
     * String type is type of bet (win,lost)
     * Money store amount of money betted
     * LocalDateTime stores time when bet was made
     * Boolean Winner is a boolean variable which is set to true/false after bet results(in scheduled bet service)
     * Odd stores odd ratio for specific bet(based on bet type)
     * Class LolBet is connected with LolMatch, User and LolTeam
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String type;

    @Min(1)
    @NotNull
    private BigDecimal money;

    private BigDecimal odd;

    private LocalDateTime date;

    private Boolean winner;

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
}
