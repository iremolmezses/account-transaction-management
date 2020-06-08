package io.blueharvest.atm.service;

import io.blueharvest.atm.model.Transaction;
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
public class TransactionService {
    @Value("${atm.api.url.template}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders headers;
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    public TransactionService(){
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public Transaction[] getAllTransactions(String accountId) {
        LOGGER.info("Getting all transactions of account {}", accountId);
        return restTemplate.exchange(String.format(apiUrl, accountId),
                                     HttpMethod.GET,
                                     new HttpEntity<>(this.headers),
                                     Transaction[].class)
                           .getBody();
    }
}
