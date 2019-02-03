package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.Shared.League;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

/**
 * FootballLeague represents data about specific football league, and
 * is inheriting common variables from League class
 */
@Entity
@Table(name = "footballLeagues")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public @Data class FootballLeague extends League {
    /**
     * Association with country, relevant for league
     */
    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonBackReference
    private Country country;

    /**
     * Association with football teams in league
     */
    @OneToMany(mappedBy = "footballLeague")
    private List<FootballTeam> footballTeams = new ArrayList<>();

    // this kind of builder works just fine, but can't wait for stable super builder from lombok
    @Builder
    public FootballLeague(Long id, @NotEmpty String name, Country country, List<FootballTeam> footballTeams) {
        super(id, name);
        this.country = country;
        this.footballTeams = footballTeams;
    }
}
