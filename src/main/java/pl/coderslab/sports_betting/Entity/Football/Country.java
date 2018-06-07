package pl.coderslab.sports_betting.Entity.Football;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "countries")
public @Data class Country {
    /** Country is a class for football matches
     *  Name is a name of country, and country has relation one to many with footballLeagues
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String name;

    @OneToMany(mappedBy = "country")
    List<FootballLeague> footballLeagues = new ArrayList<>();
}