package com.martindavidik.dataservice.service.impl;

import com.martindavidik.dataservice.domain.BankAccount;
import com.martindavidik.dataservice.repository.BankAccountRepository;
import com.martindavidik.dataservice.service.BankAccountService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    @Transactional
    public void delete(BankAccount bankAccount) {
        bankAccountRepository.delete(bankAccount);
    }
}
