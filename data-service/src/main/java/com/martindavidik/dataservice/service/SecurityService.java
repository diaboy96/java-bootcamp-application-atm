package com.martindavidik.dataservice.service;

import com.martindavidik.dataservice.pojo.HashedPassword;

public interface SecurityService {

    HashedPassword encodePassword(String password);

    boolean validatePassword(String passwordInPlainText, HashedPassword storedPassword);
}
