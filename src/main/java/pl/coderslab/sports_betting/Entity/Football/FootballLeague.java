package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "footballLeagues")
public @Data class FootballLeague {

    /**
     * FootballLeague has one main variable - String name
     * other are conections to Country and FootballName
     * Is used to name football leagues
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonBackReference
    Country country;

    @OneToMany(mappedBy = "footballLeague")
    List<FootballTeam> footballTeams = new ArrayList<>();

}
