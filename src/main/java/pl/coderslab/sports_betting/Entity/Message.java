package pl.coderslab.sports_betting.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "messages")
public @Data
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    String text;

    boolean open;

    @NotNull
    @ManyToOne
    @JoinColumn(name= "send_id")
    private User sender;

    @NotNull
    @ManyToOne
    @JoinColumn(name= "received_id")
    private User receiver;
}
