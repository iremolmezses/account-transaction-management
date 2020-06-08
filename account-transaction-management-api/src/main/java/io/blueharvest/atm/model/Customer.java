package io.blueharvest.atm.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Customer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", length = 35)
    @Size(min=2, max=35)
    @NonNull
    private String firstName;

    @Column(name = "LAST_NAME", length = 35)
    @Size(min=2, max=35)
    @NonNull
    private String lastName;

    @Column(name = "SOCIAL_SECURITY_NUMBER")
    @NonNull
    private Integer socialSecurityNumber;
}
