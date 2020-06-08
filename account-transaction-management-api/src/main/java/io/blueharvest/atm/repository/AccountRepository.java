package io.blueharvest.atm.repository;

import io.blueharvest.atm.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT a FROM Account a WHERE a.id=:id")
    @Transactional
    Optional<Account> findById(@Param("id") Long id);

    @Query("SELECT a FROM Account a INNER JOIN a.customer c WHERE c.id=:customerId")
    @Transactional
    Optional<List<Account>> findAllByCustomerId(@Param("customerId") Long customerId);
}
