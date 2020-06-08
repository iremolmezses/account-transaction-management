package io.blueharvest.atm.repository;

import io.blueharvest.atm.model.Account;
import io.blueharvest.atm.model.AccountType;
import io.blueharvest.atm.model.Customer;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository repository;

    @Test
    public void findAll() {
        final List<Customer> optionalList = repository.findAll();
        assertNotNull(optionalList);
        assertEquals(2, optionalList.size());
        Customer customer_1 = optionalList.get(0);
        assertEquals("Jane", customer_1.getFirstName());
        assertEquals("Doe", customer_1.getLastName());
        Customer customer_2 = optionalList.get(1);
        assertEquals("John", customer_2.getFirstName());
        assertEquals("Doe", customer_2.getLastName());
    }
}
