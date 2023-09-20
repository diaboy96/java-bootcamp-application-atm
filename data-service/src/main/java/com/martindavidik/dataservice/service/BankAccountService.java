package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.domain.BankAccount;

public interface BankAccountService {

    /**
     * Saves bankAccount entity to database
     *
     * @param bankAccount - entity to be saved
     *
     * @return BankAccount entity
     */
    BankAccount save(BankAccount bankAccount);

    /**
     * Deletes bankAccount entity from database
     *
     * @param bankAccount - entity to be deleted
     */
    void delete(BankAccount bankAccount);
}
