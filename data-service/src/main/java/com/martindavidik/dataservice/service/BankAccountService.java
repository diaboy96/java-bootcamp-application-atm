package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.domain.BankAccount;

public interface BankAccountService {

    BankAccount save(BankAccount bankAccount);

    void delete(BankAccount bankAccount);
}
