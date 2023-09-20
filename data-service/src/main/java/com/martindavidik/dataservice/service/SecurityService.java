package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.pojo.HashedPassword;

public interface SecurityService {

    /**
     * Encrypts password
     *
     * @param password - password in plain text
     *
     * @return HashedPassword object
     */
    HashedPassword encodePassword(String password);

    /**
     * Checks whether provided password (in plain text) is matching with password saved in database (hashed password)
     *
     * @param passwordInPlainText - provided password by user
     * @param storedPassword - password stored in database
     *
     * @return TRUE when password is VALID
     */
    boolean validatePassword(String passwordInPlainText, HashedPassword storedPassword);
}
