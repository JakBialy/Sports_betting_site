package pl.coderslab.sports_betting.Service.General.Service;

import pl.coderslab.sports_betting.Entity.Transaction;

import java.util.List;

public interface TransactionService {

    public void saveTransaction(Transaction transaction);

    public List<Transaction> findAllByUserId(Long id);


    }
