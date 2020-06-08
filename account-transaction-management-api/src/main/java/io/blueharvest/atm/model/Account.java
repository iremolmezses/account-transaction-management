package io.blueharvest.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Account")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    @Setter
    private Customer customer;

    @Column(name = "ACCOUNT_TYPE")
    @Enumerated(EnumType.STRING)
    @NonNull
    private AccountType accountType;

    @Column(name = "BALANCE")
    @NonNull
    private BigDecimal balance;
}

