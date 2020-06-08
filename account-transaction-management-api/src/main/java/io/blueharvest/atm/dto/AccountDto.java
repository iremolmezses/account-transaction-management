package io.blueharvest.atm.dto;

import io.blueharvest.atm.model.AccountType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountDto {
    @Getter
    @Setter
    private BigDecimal balance;

    @NotNull
    @Getter
    @Setter
    private AccountType accountType;

    @Override
    public String toString() {
        return "Account{" + "type=" + accountType + ", balance=" + balance + '}';
    }
}

