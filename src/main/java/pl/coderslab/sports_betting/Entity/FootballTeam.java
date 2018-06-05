package pl.coderslab.sports_betting.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "footballTeams")
public @Data
class FootballTeam {

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
    FootballLeague footballLeague;

    @OneToMany(mappedBy="homeFootballTeam")
    private List<FootballMatch> homeTeamGames = new ArrayList<>();

    @OneToMany(mappedBy="awayFootballTeam")
    private List<FootballMatch> awayTeamGames = new ArrayList<>();

    @OneToMany(mappedBy="winner")
    private List<FootballMatch> wonGames = new ArrayList<>();

    @OneToMany(mappedBy="footballTeam")
    private List<Bet> bets = new ArrayList<>();

// can be a problem for swagger
//    @ManyToMany(mappedBy="footballTeams")
//    private List<User> users = new ArrayList<>();
}
