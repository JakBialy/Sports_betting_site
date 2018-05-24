package pl.coderslab.sports_betting.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public @Data
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    private String name;
}
