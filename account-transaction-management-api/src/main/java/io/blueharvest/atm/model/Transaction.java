package io.blueharvest.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Transaction")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    @NonNull
    @Setter
    private Account account;

    @Column(name = "AMOUNT")
    @NonNull
    private BigDecimal amount;

    @Column(name = "TRANSACTION_DATE")
    @Type(type = "java.time.LocalDate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @NonNull
    private LocalDate date;

    @Column(name = "DESCRIPTION")
    @Setter
    private String description;
}
