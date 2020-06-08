package io.blueharvest.atm.repository;

import io.blueharvest.atm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Transactional
    @Query("SELECT t FROM Transaction t INNER JOIN t.account a WHERE a.id=:accountId")
    Optional<List<Transaction>> findAllByAccountId(@Param("accountId") Long accountId);
}
