package io.blueharvest.atm.service;

import io.blueharvest.atm.exception.EntityNotFoundException;
import io.blueharvest.atm.model.Account;
import io.blueharvest.atm.model.Transaction;
import io.blueharvest.atm.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public List<Transaction> getAllByAccountId(Long accountId) {
        Optional<List<Transaction>> transactions = repository.findAllByAccountId(accountId);

        if (!transactions.isPresent()){
            throw new EntityNotFoundException("Not found any transactions for account " + accountId);
        }

        return transactions.get();
    }

    @Transactional
    @Modifying
    public Transaction create(Long accountId, Transaction transaction) {
        Account account = accountService.findById(accountId);
        transaction.setAccount(account);
        return repository.save(transaction);
    }
}
