package pl.coderslab.sports_betting.Service.General.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Transaction;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.General.TransactionRepository;
import pl.coderslab.sports_betting.Repository.General.UserRepository;
import pl.coderslab.sports_betting.Service.General.Service.TransactionService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * Method is saving transaction
     * first method is looking for actual user, then is adding into user account money
     * user is saved, user and time(now) are set into transaction
     * transaction is saved into db
     * @param transaction Object transaction
     */

    @Transactional
    public void saveTransaction(Transaction transaction){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setMoney(user.getMoney().add(transaction.getAmount()));
        userRepository.save(user);

        transaction.setUser(user);
        transaction.setCreated(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    /**
     * Method is looking for list of transactions based on user id
     * @param id user Id
     * @return list of transactions
     */
    public List<Transaction> findAllByUserId(Long id){
        List<Transaction> list = transactionRepository.findAllByUserId(id);
        return list;
    }


}
