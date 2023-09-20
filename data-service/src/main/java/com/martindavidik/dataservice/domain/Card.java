package com.martindavidik.dataservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @ManyToOne
    @JoinColumn(name = "bankAccountId", nullable = false)
    @NonNull
    private BankAccount bankAccount;

    @Id
    @Column(unique = true)
    @Size(min = 16, max = 19)
    @NonNull
    private String cardNumber;

    @Size(min = 2, max = 2)
    @NonNull
    private String expiryMonth;

    @Size(min = 2, max = 2)
    @NonNull
    private String expiryYear;

    @Size(min = 3, max = 4)
    @NonNull
    private String cvv;

    private String pinCodeHash;

    private int failedPinAttempts = 0;

    private boolean active = true;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Card card = (Card) o;
        return Objects.equals(getCardNumber(), card.getCardNumber());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
