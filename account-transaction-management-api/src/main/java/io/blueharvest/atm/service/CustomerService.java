package io.blueharvest.atm.service;

import io.blueharvest.atm.controller.AccountRestController;
import io.blueharvest.atm.exception.EntityExistsException;
import io.blueharvest.atm.exception.EntityNotFoundException;
import io.blueharvest.atm.model.Customer;
import io.blueharvest.atm.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository repository;

    @Transactional
    public Customer findById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (!customer.isPresent()){
            LOGGER.error("Customer with the given id doesn't exists");
            throw new EntityNotFoundException("Customer with the given id doesn't exist");
        }
        return customer.get();
    }

    @Transactional
    public List<Customer> findAll(){
        return repository.findAll();
    }

    @Transactional
    @Modifying
    public Customer create(Customer customer) {
        Optional<Customer> existingCustomer = repository.findBySocialSecurityNumber(customer.getSocialSecurityNumber());
        if (existingCustomer.isPresent()) {
            Customer c = existingCustomer.get();
            LOGGER.error("Customer with the social security number already exists: {} {}", c.getFirstName(), c.getLastName());
            throw new EntityExistsException("Customer with the given social security number already exists:"
                                             + customer.getSocialSecurityNumber());
        }

        return repository.save(customer);
    }

    @Transactional
    @Modifying
    public void delete(Long customerId){
        Customer customer = repository.findById(customerId).orElseThrow(() ->
                new EntityNotFoundException("Customer with the given id doesn't exist:" + customerId));
        repository.delete(customer);
    }
}
