package com.martindavidik.dataservice.repository;

import com.martindavidik.dataservice.domain.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer> {
}
