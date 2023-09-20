package com.martindavidik.dataservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankAccountId;

    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false)
    @NonNull
    private Client client;

    private double balance = 0;

    @OneToMany(mappedBy = "bankAccount")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<Card> cards = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BankAccount bankAccount = (BankAccount) o;
        return Objects.equals(getBankAccountId(), bankAccount.getBankAccountId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
