package io.blueharvest.atm.service;

import io.blueharvest.atm.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    @Value("${atm.api.url.template}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders headers;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    public CustomerService(){
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public Customer[] getAllCustomers() {
        Customer[] customers = restTemplate.exchange(apiUrl,
                                                     HttpMethod.GET,
                                                     new HttpEntity<>(this.headers),
                                                     Customer[].class)
                                           .getBody();
        return customers;
    }

    public Customer createCustomer(Customer customer) {
        HttpEntity<Customer> request = new HttpEntity<>(customer, this.headers);
        return restTemplate.postForObject(apiUrl, request, Customer.class);
    }

    public void deleteCustomer(String customerId) {
        restTemplate.exchange(apiUrl.concat(String.format("/%s", customerId)),
                              HttpMethod.DELETE,
                              new HttpEntity<>(this.headers),
                              Void.class);
    }
}
