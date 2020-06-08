package io.blueharvest.atm.controller;

import io.blueharvest.atm.dto.TransactionDto;
import io.blueharvest.atm.model.Transaction;
import io.blueharvest.atm.service.TransactionService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TransactionRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestController.class);

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "accounts/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getAllTransactionsOfAccount(@Valid @Min(value = 1) @PathVariable Long accountId) {
        LOGGER.info("Getting all transactions of account {}", accountId);
        return new ResponseEntity<>(transactionService.getAllByAccountId(accountId), HttpStatus.OK);
    }

    @PostMapping(value = "accounts/{accountId}/transactions", consumes = "application/json")
    public ResponseEntity<Transaction> saveTransaction(@Valid @Min(value = 1) @PathVariable Long accountId,
                                                       @RequestBody TransactionDto transaction) {
        LOGGER.info("Saving transaction with amount {} @ {}", transaction.getAmount(), transaction.getDate());
        return new ResponseEntity<>(transactionService.create(accountId, modelMapper.map(transaction, Transaction.class)),
                                    HttpStatus.CREATED);
    }
}
