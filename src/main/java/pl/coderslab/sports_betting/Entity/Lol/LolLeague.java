package pl.coderslab.sports_betting.Entity.Lol;

import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "lolLeagues")
public @Data class LolLeague {

    /**
     * LolLeague has one main variable - String name
     * other are conections to Country and FootballName
     * Is used to name football leagues
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String name;

    @OneToMany(mappedBy = "lolLeague")
    List<LolTeam> lolTeams = new ArrayList<>();

}
