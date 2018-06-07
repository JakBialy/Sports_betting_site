package pl.coderslab.sports_betting.Service.General.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.coderslab.sports_betting.Entity.Transaction;
import pl.coderslab.sports_betting.Entity.User;
import pl.coderslab.sports_betting.Repository.General.TransactionRepository;
import pl.coderslab.sports_betting.Repository.General.UserRepository;
import pl.coderslab.sports_betting.Service.General.Service.TransactionService;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
        BigDecimal check = user.getMoney().subtract(transaction.getAmount());
        int transactionNumSize = findAllByUserId(user.getId()).size() + 1;

        if ((transaction.getType().equals("TransferBack"))&&(user.getMoney().compareTo(user.getMoney().subtract(transaction.getAmount())) <= 0)){

            user.setMoney(user.getMoney().subtract(transaction.getAmount()));
            transaction.setAmount(transaction.getAmount().subtract(transaction.getAmount().multiply(BigDecimal.valueOf(2))));
            saveTransactionSavingPack(transaction, user);

        } else if (transaction.getType().equals("Card")){
            if (transactionNumSize % 3 == 0){
                transaction.setAmount(transaction.getAmount().multiply(BigDecimal.valueOf(1.05)));
            }
            user.setMoney(user.getMoney().add(transaction.getAmount()));
            saveTransactionSavingPack(transaction, user);

        } else if (transaction.getType().equals("Transfer")){
            if (transactionNumSize % 3 == 0){
                transaction.setAmount(transaction.getAmount().multiply(BigDecimal.valueOf(1.05)));
            }
            user.setMoney(user.getMoney().add(transaction.getAmount()));
            saveTransactionSavingPack(transaction, user);
        }

    }

    private void saveTransactionSavingPack(Transaction transaction, User user) {
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
