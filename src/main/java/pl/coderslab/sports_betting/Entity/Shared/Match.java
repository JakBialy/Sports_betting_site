package pl.coderslab.sports_betting.Entity.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 *  Abstract class having common fields for all type of matches
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public @Data
abstract class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * match start time
     */
    private LocalDateTime start;

    /**
     * match end time
     */
    private LocalDateTime end;

    /**
     * Actual status of a match
     */
    private String status;

    /**
     * score for home team
     */
    private int homeScore;

    /**
     * score for away team
     */
    private int awayScore;

    /**
     * predefined as a false - after bet settlement (bet scheduled service) parameter is changed into true
     */
    private boolean checked = false;
}
