package io.blueharvest.atm.controller;

import io.blueharvest.atm.dto.AccountDto;
import io.blueharvest.atm.model.Account;
import io.blueharvest.atm.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/customers/{customerId}/accounts")
    public ResponseEntity<List<Account>> getAllAccountsOfClient(@Valid @Min(value = 1) @PathVariable Long customerId) {
        return new ResponseEntity<>(accountService.findAllByCustomerId(customerId), HttpStatus.OK);
    }

    @PostMapping(value = "/customers/{customerId}/accounts", consumes = "application/json")
    public ResponseEntity<Account> saveAccount(@Valid @Min(value = 1) @PathVariable Long customerId,
                                               @Valid @RequestBody AccountDto account) {
        LOGGER.info("Creating a new {} account for customer:{}", account.getAccountType().toString(), customerId);
        return new ResponseEntity<>(accountService.create(customerId, modelMapper.map(account, Account.class)),
                                    HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/accounts/{accountId}")
    public ResponseEntity deleteAccount(@Valid @Min(value = 1) @PathVariable Long accountId) {
        LOGGER.info("Deleting the account {}", accountId);
        accountService.delete(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
