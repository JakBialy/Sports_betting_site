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
