package pl.coderslab.sports_betting.Entity.Lol;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.coderslab.sports_betting.Entity.Shared.League;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


/**
 * LolLeague represents data about specific League of Legends league, and
 * is inheriting common variables from League class
 */
@Entity
@Table(name = "lolLeagues")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public @Data class LolLeague extends League {
    /**
     * Association with League of Legends teams for specific league
     */
    @OneToMany(mappedBy = "lolLeague")
    private List<LolTeam> lolTeams = new ArrayList<>();

    // this kind of builder works just fine, but can't wait for stable super builder from lombok
    public LolLeague(Long id, @NotEmpty String name, List<LolTeam> lolTeams) {
        super(id, name);
        this.lolTeams = lolTeams;
    }
}
