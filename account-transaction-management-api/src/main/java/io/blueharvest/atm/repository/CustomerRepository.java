package io.blueharvest.atm.repository;

import io.blueharvest.atm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Transactional
    @Query("SELECT c FROM Customer c where c.id=:id")
    Optional<Customer> findById(@Param("id") Long id);

    @Transactional
    @Query("SELECT c FROM Customer c where c.socialSecurityNumber=:ssn")
    Optional<Customer> findBySocialSecurityNumber(@Param("ssn") Integer ssn);
}
