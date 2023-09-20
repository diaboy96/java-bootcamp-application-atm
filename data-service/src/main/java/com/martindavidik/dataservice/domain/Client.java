package com.martindavidik.dataservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clientId;

    @Size(min = 2, max = 100)
    @NonNull
    private String name;

    @Size(min = 2, max = 100)
    @NonNull
    private String surname;

    @Size(min = 9)
    @NonNull
    private String birthnumber;

    @Column(unique = true)
    @Email
    @NonNull
    private String email;

    @OneToMany(mappedBy = "client")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude
    private Set<BankAccount> bankAccounts = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Client client = (Client) o;
        return Objects.equals(getClientId(), client.getClientId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
