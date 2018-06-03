package pl.coderslab.sports_betting.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public @Data
class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    LocalDateTime created;

    BigDecimal amount;

    String type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    User user;
}
