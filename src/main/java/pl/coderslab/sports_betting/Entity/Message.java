package pl.coderslab.sports_betting.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public @Data
class Message {
    /**
     * Message is responsible for creating object which
     * can be read by two Users
     * Two dependencies are connected with same class - User
     */
       @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String text;

    private boolean open;

    @NotNull
    @ManyToOne
    @JoinColumn(name= "send_id")
    private User sender;

    @NotNull
    @ManyToOne
    @JoinColumn(name= "received_id")
    private User receiver;
}
