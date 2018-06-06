package pl.coderslab.sports_betting.Entity;

import lombok.Data;
import pl.coderslab.sports_betting.Entity.Football.FootballBet;
import pl.coderslab.sports_betting.Entity.Football.FootballTeam;
import pl.coderslab.sports_betting.Entity.Lol.LolTeam;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
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
    @Email
    // in forms named e-mail
    private String username;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String lastName;

    @NotEmpty
    @Size(min = 2, max = 30)
    private String nick;

    @NotEmpty
    @Size(min = 3, max = 255)
    private String password;

    private boolean enabled;

    private BigDecimal money;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy="user")
    private List<FootballBet> footballBets = new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<Transaction> transactions = new ArrayList<>();

    @ManyToMany
    private List<FootballTeam> favoriteFootballTeams = new ArrayList<>();

    @ManyToMany
    private List<LolTeam> favoriteLolTeams = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="friends",
            joinColumns=@JoinColumn(name="personId"),
            inverseJoinColumns=@JoinColumn(name="friendId")
    )
    private List<User> friends;

    @ManyToMany
    @JoinTable(name="friends",
            joinColumns=@JoinColumn(name="friendId"),
            inverseJoinColumns=@JoinColumn(name="personId")
    )
    private List<User> friendOf;

    @OneToMany(mappedBy = "sender")
    private List<Message> send = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    private List<Message> received = new ArrayList<>();
}
