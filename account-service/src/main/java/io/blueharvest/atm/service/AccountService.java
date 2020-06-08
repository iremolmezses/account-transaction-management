package io.blueharvest.atm.service;

import io.blueharvest.atm.model.Account;
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
public class AccountService {
    @Value("${atm.api.url.template}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders headers;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    public AccountService(){
        this.headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public Account[] getAllAccounts(String clientId) {
        Account[] accounts = restTemplate.exchange(String.format(apiUrl, clientId),
                                                   HttpMethod.GET,
                                                   new HttpEntity<>(this.headers),
                                                   Account[].class)
                                         .getBody();
        return accounts;
    }


    public Account saveAccount(String clientId, Account account) {
        HttpEntity<Account> request = new HttpEntity<>(account, this.headers);
        return restTemplate.postForObject(String.format(apiUrl, clientId), request, Account.class);
    }

    public void deleteAccount(String accountId) {
        restTemplate.exchange(String.format("http://localhost:8080/api/accounts/%s", accountId),
                              HttpMethod.DELETE,
                              new HttpEntity<>(this.headers),
                              Void.class);
    }
}
