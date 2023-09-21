package com.martindavidik.dataservice.service.impl;

import com.martindavidik.dataservice.pojo.HashedPassword;
import com.martindavidik.dataservice.service.SecurityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class SecurityServiceTest {

    @Autowired
    SecurityService securityService;

    private static final String PLAIN_TEXT_PASSWORD = "s3cr3t-passw0rd";

    @Test
    public void givenPlaintextPassword_whenEncodePassword_thenEncodedPasswordDoesNotMatch() {
        // When
        HashedPassword hashedPassword1 = securityService.encodePassword(PLAIN_TEXT_PASSWORD);
        HashedPassword hashedPassword2 = securityService.encodePassword(PLAIN_TEXT_PASSWORD);

        // Then
        assertThat(hashedPassword1.getHash()).isNotEqualTo(hashedPassword2.getHash()); // hashes differ because different salt
    }

    @Test
    public void givenPlaintextAndHashedPassword_whenValidatePassword_thenHashesMatch() {
        // Given
        HashedPassword hashedPassword = securityService.encodePassword(PLAIN_TEXT_PASSWORD);

        // When
        assertThat(securityService.validatePassword(PLAIN_TEXT_PASSWORD, hashedPassword)).isTrue();
    }

    @Test
    public void givenDifferentPasswords_whenValidatePassword_thenHashesDoesNotMatch() {
        // Given
        HashedPassword hashedPassword = securityService.encodePassword(PLAIN_TEXT_PASSWORD);
        String differentPassword = "diff3r3nt-passw0rd";

        // When
        assertThat(securityService.validatePassword(differentPassword, hashedPassword)).isFalse();
    }
}
