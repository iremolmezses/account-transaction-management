package io.blueharvest.atm.controller;

import io.blueharvest.atm.model.Account;
import io.blueharvest.atm.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@Slf4j
@RequestMapping("customer")
public class AccountRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/{customerId}/accounts")
    public ResponseEntity<Account[]> getAllAccountsOfClient(@Valid @Min(value = 1) @PathVariable String customerId)
    {
        LOGGER.info("Getting all accounts of customer:{}", customerId);
        return new ResponseEntity<>(accountService.getAllAccounts(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/{customerId}/accounts", consumes = "application/json")
    public ResponseEntity<Account> saveAccount(@Valid @Min(value = 1) @PathVariable String customerId,
                                  @Valid @RequestBody Account account) {
        LOGGER.info("Creating a new account for customer:{}", customerId);
        return new ResponseEntity<>(accountService.saveAccount(customerId, account), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{customerId}/accounts/{accountId}")
    public ResponseEntity deleteAccount(@Valid @Min(value = 1) @PathVariable String customerId,
                                        @Valid @Min(value = 1) @PathVariable String accountId) {
        LOGGER.info("Deleting account:{}", accountId);
        accountService.deleteAccount(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
