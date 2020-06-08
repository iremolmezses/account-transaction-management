package io.blueharvest.atm.repository;


import io.blueharvest.atm.model.Account;
import io.blueharvest.atm.model.AccountType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AccountRepositoryTest {
    @Autowired
    private AccountRepository repository;


    @Test
    public void findAllByCustomerId() {
        final Optional<List<Account>> optionalList = repository.findAllByCustomerId(1L);
        assertTrue(optionalList.isPresent());
        List<Account> accounts = optionalList.get();
        assertEquals(2, accounts.size());
        assertEquals(AccountType.DEBIT, accounts.get(0).getAccountType());
        assertEquals(new BigDecimal("990.1000"), accounts.get(0).getBalance());
        assertEquals(AccountType.CREDIT, accounts.get(1).getAccountType());
        assertEquals(new BigDecimal("-26.8000"), accounts.get(1).getBalance());
    }
}




