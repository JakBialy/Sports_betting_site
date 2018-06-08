package pl.coderslab.sports_betting.Entity.Football;

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
@Table(name = "footballBets")
public @Data
class FootballBet {

    /**
     * Football bet is a class for saving bet parameter
     * String type is type of bet (win,draw,lost)
     * Money store amount of money betted
     * LocalDateTime stores time when bet was made
     * Boolean Winner is a boolean variable which is set to true/false after bet results(in scheduled bet service)
     * Odd stores odd ratio for specific bet(based on bet type)
     * Class FootballBet is connected with FootballMatch, User and FootballTeam
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String type;

    @Min(1)
    @NotNull
    private BigDecimal money;

    private LocalDateTime date;

    private Boolean winner;

    private BigDecimal odd;

    private float percentage;

    private Boolean accepted = true;

    private Boolean groupBet = false;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    FootballMatch footballMatch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name = "extra_id")
    @JsonBackReference
    User extra;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    FootballTeam footballTeam;
}
