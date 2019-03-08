package pl.coderslab.sports_betting.Entity.SharedAbstractEntites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class collecting basic variables for all type of teams
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public @Data
abstract class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Team name
     */
    private String team;

    /**
     * Team position in table of results
     */
    private int position;

    /**
     * Number of team win matches
     */
    private int wins;

    /**
     * Number of team lost matches
     */
    private int lost;

    /**
     * Ratio of win to lost matches
     */
    private double winLostRatio = 0.00;
}
