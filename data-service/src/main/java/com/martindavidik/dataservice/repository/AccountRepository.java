package com.martindavidik.dataservice.repository;

import com.martindavidik.dataservice.domain.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {
}
