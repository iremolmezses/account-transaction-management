package io.blueharvest.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("accountType")
    private String accountType;

    @JsonProperty("balance")
    private BigDecimal balance;
}
