package pl.coderslab.sports_betting.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "messages")
public @Data
class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String text;

    boolean open;

    @ManyToOne
    @JoinColumn(name= "send_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name= "received_id")
    private User receiver;
}
