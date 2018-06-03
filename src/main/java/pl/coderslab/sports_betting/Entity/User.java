package pl.coderslab.sports_betting.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public @Data class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty
    @Column(nullable = false, unique = true)
    // in forms named e-mail
    private String username;

    private String firstName;

    private String lastName;

    private String nick;

    @NotEmpty
    private String password;

    private boolean enabled;

    private BigInteger money;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy="user")
    private List<Bet> bets = new ArrayList<>();
}
