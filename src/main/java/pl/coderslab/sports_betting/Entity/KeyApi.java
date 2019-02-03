package pl.coderslab.sports_betting.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
public @Data
class KeyApi {
    /**
     * KeyApi is responsible for make object
     * to create APIkey, String code is the key(name key was forbidden)
     * and is depended with User, to have access and see which user has which APIkeys
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
