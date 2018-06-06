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

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    FootballMatch footballMatch;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    FootballTeam footballTeam;
}
