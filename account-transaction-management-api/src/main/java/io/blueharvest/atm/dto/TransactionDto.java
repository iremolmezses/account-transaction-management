package io.blueharvest.atm.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {

    @Getter
    @Setter
    @NotNull
    private BigDecimal amount;

    @Getter
    @Setter
    @NotNull
    private LocalDate date;

    @Getter
    @Setter
    private String description;

    @Override
    public String toString() {
        return "TransactionDto{" + "date=" + date + ", amount=" + amount + '}';
    }
}
