package io.blueharvest.atm.controller;

import io.blueharvest.atm.model.Customer;
import io.blueharvest.atm.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@Slf4j
@RequestMapping("/customers")
public class CustomerRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

    @Autowired
    private CustomerService customerService;


    @GetMapping
    public ResponseEntity<Customer[]> getAllCustomers() {
        LOGGER.info("Getting all customers");
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        LOGGER.info("Creating new customer:{} {}", customer.getFirstName(), customer.getLastName());
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{customerId}")
    @ResponseStatus(NO_CONTENT)
    public ResponseEntity<String> deleteCustomer(@Valid @Min(value=1) @PathVariable String customerId) {
        LOGGER.info("Deleting customer: {}", customerId);
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
