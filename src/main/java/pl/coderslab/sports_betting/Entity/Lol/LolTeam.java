package pl.coderslab.sports_betting.Entity.Lol;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lolTeams")
public @Data
class LolTeam {

    /**
     * Entity LolTeam gives are statistics about team,
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String team;

    int position;

    int wins;

    int lost;

    Double winLostRatio = 0.00;

    @ManyToOne
    @JoinColumn(name = "league_id")
    @JsonBackReference
    LolLeague lolLeague;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="homeLolTeam")
    private List<LolMatch> homeTeamGames = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy="awayLolTeam")
    private List<LolMatch> awayTeamGames = new ArrayList<>();

    @OneToMany(mappedBy="winner")
    private List<LolMatch> wonGames = new ArrayList<>();

    @OneToMany(mappedBy="lolTeam")
    private List<LolBet> lolBets = new ArrayList<>();

    @JsonIgnore
    public List<LolBet> getLolBets() {
        return lolBets;
    }
// can be a problem for swagger
//    @ManyToMany(mappedBy="footballTeams")
//    private List<User> users = new ArrayList<>();
}
