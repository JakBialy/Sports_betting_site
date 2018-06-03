package pl.coderslab.sports_betting.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.sports_betting.Entity.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUserId (Long id);
}
