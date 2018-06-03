package pl.coderslab.sports_betting.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "bets")
public @Data
class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String type;

    @NotNull
    private BigDecimal money;

    private LocalDateTime date;

    private Boolean winner;

    @ManyToOne
    @JoinColumn(name = "match_id")
    @JsonBackReference
    Match match;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name = "team_id")
    @JsonBackReference
    Team team;
}
