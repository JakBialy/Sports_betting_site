package pl.coderslab.sports_betting.Entity.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class storing common variables for all type of odds
 * which are created by random generator (odd scheduled service)
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public @Data
abstract class Odds {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * bookmaker for odds
     */
    private String bookmaker;

    /**
     * odds for home team
     */
    private double oddHome;

    /**
     * odds for away team
     */
    private double oddAway;
}
