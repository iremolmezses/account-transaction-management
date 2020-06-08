package io.blueharvest.atm.controller;


import io.blueharvest.atm.model.Transaction;
import io.blueharvest.atm.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@Slf4j
@RequestMapping("/transactions")
public class TransactionRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    @Autowired
    private TransactionService transactionService;


    @GetMapping(value = "/{accountId}")
    public ResponseEntity<Transaction[]> getAllAccountsOfClient(@PathVariable String accountId) {
        LOGGER.info("Getting all transactions of account {}", accountId);
        return new ResponseEntity<>(transactionService.getAllTransactions(accountId), HttpStatus.OK);
    }
}
