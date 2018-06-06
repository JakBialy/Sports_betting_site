package pl.coderslab.sports_betting.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class KeyApi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(unique = true)
    String code;
}
