package pl.coderslab.sports_betting.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
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
    private List<Match> homeTeamGames = new ArrayList<>();

    @OneToMany(mappedBy="awayTeam")
    private List<Match> awayTeamGames = new ArrayList<>();

    @OneToMany(mappedBy="winner")
    private List<Match> wonGames = new ArrayList<>();

    @OneToMany(mappedBy="team")
    private List<Bet> bets = new ArrayList<>();

// can be a problem for swagger
//    @ManyToMany(mappedBy="favoriteTeams")
//    private List<User> users = new ArrayList<>();
}
