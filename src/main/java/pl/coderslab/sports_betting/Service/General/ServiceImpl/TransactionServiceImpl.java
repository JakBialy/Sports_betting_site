package pl.coderslab.sports_betting.Service.General.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Transaction;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.TransactionRepository;
import pl.coderslab.sports_betting.Repository.UserRepository;
import pl.coderslab.sports_betting.Service.General.Service.TransactionService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    public void saveTransaction(Transaction transaction){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setMoney(user.getMoney().add(transaction.getAmount()));
        userRepository.save(user);

        transaction.setUser(user);
        transaction.setCreated(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    public List<Transaction> findAllByUserId(Long id){
        List<Transaction> list = transactionRepository.findAllByUserId(id);
        return list;
    }


}
