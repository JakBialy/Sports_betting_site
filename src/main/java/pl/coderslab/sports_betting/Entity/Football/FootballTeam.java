package pl.coderslab.sports_betting.Entity.Football;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "footballTeams")
public @Data
class FootballTeam {

    /**
     * Entity FootballTeam gives are statistics about team,
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String team;

    private int position;

    private int wins;

    private int draws;

    private int lost;

    private Double winLostRatio = 0.00;

    @ManyToOne
    @JoinColumn(name = "league_id")
    @JsonBackReference
    private FootballLeague footballLeague;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="homeFootballTeam")
    private List<FootballMatch> homeTeamGames = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy="awayFootballTeam")
    private List<FootballMatch> awayTeamGames = new ArrayList<>();

    @OneToMany(mappedBy="winner")
    private List<FootballMatch> wonGames = new ArrayList<>();

    @OneToMany(mappedBy="footballTeam")
    private List<FootballBet> footballBets = new ArrayList<>();

    @JsonIgnore
    public List<FootballBet> getFootballBets() {
        return footballBets;
    }

}
