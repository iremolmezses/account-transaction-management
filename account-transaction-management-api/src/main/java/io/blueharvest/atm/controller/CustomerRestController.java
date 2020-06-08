package io.blueharvest.atm.controller;

import io.blueharvest.atm.dto.CustomerDto;
import io.blueharvest.atm.model.Customer;
import io.blueharvest.atm.service.CustomerService;
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
@RequestMapping(value = "/api/customers")
public class CustomerRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<Customer>> findAllCustomers() {
        LOGGER.info("Getting all customers");
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findCustomerById(@Valid @Min(value = 1) @PathVariable Long id) {
        LOGGER.info("Getting a customer with id {}", id);
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@Valid @Min(value = 1) @PathVariable Long id) {
        LOGGER.info("Deleting the customer with id {}", id);
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody CustomerDto customer) {
        LOGGER.info("Creating customer {} {}", customer.getFirstName(), customer.getLastName());
        return new ResponseEntity<>(customerService.create(modelMapper.map(customer, Customer.class)),
                                    HttpStatus.CREATED);
    }
}
