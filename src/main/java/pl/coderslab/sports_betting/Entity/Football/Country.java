package pl.coderslab.sports_betting.Entity.Football;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * Specific class for football, storing information about country
 */
@Entity
@Table(name = "countries")
public @Data class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * name of country
     */
    @NotEmpty
    private String name;

    /**
     * association with football leagues in specific country
     */
    @OneToMany(mappedBy = "country")
    private List<FootballLeague> footballLeagues = new ArrayList<>();
}