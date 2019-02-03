package pl.coderslab.sports_betting.Service.General.Service;

import pl.coderslab.sports_betting.Entity.Transaction;

import java.util.List;

public interface TransactionService {
     void saveTransaction(Transaction transaction);
     List<Transaction> findAllByUserId(Long id);
}
