package pl.coderslab.sports_betting.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams")
public @Data
class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String team;

    int position;

    int wins;

    int draws;

    int lost;

    @ManyToOne
    @JoinColumn(name = "league_id")
    @JsonBackReference
    League league;

    @OneToMany(mappedBy="homeTeam")
    private List<Match> homeTeamGames;

    @OneToMany(mappedBy="awayTeam")
    private List<Match> awayTeamGames;

}
