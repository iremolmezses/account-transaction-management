package io.blueharvest.atm.repository;

import io.blueharvest.atm.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository repository;


    @Test
    public void findAllByAccountId() {
        final Optional<List<Transaction>> optionalList = repository.findAllByAccountId(1L);
        assertTrue(optionalList.isPresent());
        List<Transaction> transactions = optionalList.get();
        assertEquals(2, transactions.size());
        Transaction transaction_1 = transactions.get(0);
        assertEquals(new BigDecimal("-9.9000"), transaction_1.getAmount());
        assertEquals(LocalDate.of(2020, 6, 6), transaction_1.getDate());
        assertEquals("AH", transaction_1.getDescription());
        Transaction transaction_2 = transactions.get(1);
        assertEquals(new BigDecimal("1000.0000"), transaction_2.getAmount());
        assertEquals(LocalDate.of(2020, 6, 6), transaction_2.getDate());
        assertEquals("Initial balance", transaction_2.getDescription());
    }
}
