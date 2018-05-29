package pl.coderslab.sports_betting.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "leagues")
public @Data class League {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonBackReference
    Country country;

    @OneToMany(mappedBy = "league")
    List<Team> teams = new ArrayList<>();

}
