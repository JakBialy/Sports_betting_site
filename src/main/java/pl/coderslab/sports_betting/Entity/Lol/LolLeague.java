package pl.coderslab.sports_betting.Entity.Lol;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "lolLeagues")
public @Data class LolLeague {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String name;

    @OneToMany(mappedBy = "lolLeague")
    List<LolTeam> lolTeams = new ArrayList<>();

}
