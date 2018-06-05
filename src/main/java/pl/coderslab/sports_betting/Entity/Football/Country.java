package pl.coderslab.sports_betting.Entity.Football;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "countries")
public @Data class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String name;

    @OneToMany(mappedBy = "country")
    List<FootballLeague> footballLeagues = new ArrayList<>();
}