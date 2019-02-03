package pl.coderslab.sports_betting.Entity.Shared;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;

/**
 * Abstract class collecting common data for all type of leagues
 */
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public @Data
abstract class League {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    /**
     * League name
     */
    @NotEmpty
    String name;
}
