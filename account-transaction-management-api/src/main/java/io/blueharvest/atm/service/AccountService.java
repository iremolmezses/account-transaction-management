package io.blueharvest.atm.service;

import io.blueharvest.atm.exception.EntityNotFoundException;
import io.blueharvest.atm.model.Account;
import io.blueharvest.atm.model.Customer;
import io.blueharvest.atm.model.Transaction;
import io.blueharvest.atm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TransactionService transactionService;

    @Transactional
    public Account findById(Long accountId) {
        Optional<Account> account = repository.findById(accountId);
        if (!account.isPresent()){
            throw new EntityNotFoundException("Account with the given id doesn't exist");
        }
        return account.get();
    }

    @Transactional
    public List<Account> findAllByCustomerId(Long customerId) {
        Optional<List<Account>> accounts = repository.findAllByCustomerId(customerId);

        if(!accounts.isPresent()){
            throw new EntityNotFoundException("Not found any account for customer " + customerId);
        }
        return accounts.get();
    }

    @Transactional
    @Modifying
    public Account create(Long customerId, Account account) {
        Customer customer = customerService.findById(customerId);
        account.setCustomer(customer);
        account = repository.save(account);

        if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            Transaction transaction = new Transaction(account, account.getBalance(), LocalDate.now());
            transaction.setDescription("Initial balance");
            transactionService.create(account.getId(), transaction);
        }
        return account;
    }

    @Transactional
    @Modifying
    public void delete(Long accountId){
        Account account = repository.findById(accountId).orElseThrow(() ->
                new EntityNotFoundException("Account with the given id doesn't exist" + accountId));

        repository.delete(account);
    }
}
