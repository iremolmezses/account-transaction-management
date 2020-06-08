package io.blueharvest.atm.service;

import io.blueharvest.atm.exception.EntityNotFoundException;
import io.blueharvest.atm.model.Account;
import io.blueharvest.atm.model.AccountType;
import io.blueharvest.atm.model.Customer;
import io.blueharvest.atm.model.Transaction;
import io.blueharvest.atm.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;

    @Mock
    CustomerService customerService;

    @InjectMocks
    private AccountService accountService;

    private Customer customer;
    private List<Account> accountList;

    @Before
    public void setup() {
        this.customer = new Customer(5L, "Bob", "Johnson", 12345);
        this.accountList = Arrays.asList(new Account(3L, customer, AccountType.DEBIT, BigDecimal.valueOf(100)),
                                                  new Account(7L, customer, AccountType.SAVINGS, BigDecimal.valueOf(200)));
        when(accountRepository.findAllByCustomerId(5L)).thenReturn(Optional.of(accountList));
        when(customerService.findById(5L)).thenReturn(this.customer);
        when(customerService.findById(123L)).thenThrow(new EntityNotFoundException("Customer with given id not found"));
    }

    @Test
    public void findAllByExistingCustomerId() {
        List<Account> accounts = accountService.findAllByCustomerId(5L);
        assertNotNull(accounts);
        assertEquals(2, accounts.size());

        Account account_1 = accounts.get(0);
        assertEquals("Bob", account_1.getCustomer().getFirstName());
        assertEquals("Johnson", account_1.getCustomer().getLastName());
        assertTrue(account_1.getId().equals(3L));
        assertEquals(AccountType.DEBIT, account_1.getAccountType());
        assertEquals(BigDecimal.valueOf(100), account_1.getBalance());

        Account account_2 = accounts.get(1);
        assertEquals("Bob", account_2.getCustomer().getFirstName());
        assertEquals("Johnson", account_2.getCustomer().getLastName());
        assertTrue(account_2.getId().equals(7L));
        assertEquals(AccountType.SAVINGS, account_2.getAccountType());
        assertEquals(BigDecimal.valueOf(200), account_2.getBalance());
    }

    @Test(expected = EntityNotFoundException.class)
    public void findAllByNonExistingCustomerId() {
        accountService.findAllByCustomerId(1L);
    }

    @Test
    public void createNewAccountWithExistingCustomerId() {
        Account account = new Account(AccountType.DEBIT, BigDecimal.valueOf(0));
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountService.create(5L, account);

        assertEquals(this.customer, result.getCustomer());
        assertEquals(BigDecimal.valueOf(0), result.getBalance());
    }

    @Test(expected = EntityNotFoundException.class)
    public void createNewAccountWithNonExistingCustomerId() {
        Account account = new Account(AccountType.DEBIT, BigDecimal.valueOf(0));
        lenient().when(accountRepository.save(account)).thenReturn(account);

        accountService.create(123L, account);
    }
}
