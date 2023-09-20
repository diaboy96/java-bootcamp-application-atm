package com.martindavidik.dataservice.service.impl;

import com.martindavidik.dataservice.pojo.HashedPassword;
import com.martindavidik.dataservice.service.SecurityService;
import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class SecurityImpl implements SecurityService {

    private static final int ITERATIONS = 2;
    private static final int MEMORY_LIMIT = 66536;
    private static final int HASH_LENGTH = 32;
    private static final int PARALLELISM = 1;

    private HashedPassword encrypt(String password, byte[] salt) {
        Argon2Parameters.Builder builder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                .withIterations(ITERATIONS)
                .withMemoryAsKB(MEMORY_LIMIT)
                .withParallelism(PARALLELISM)
                .withSalt(salt);

        Argon2BytesGenerator generate = new Argon2BytesGenerator();
        generate.init(builder.build());
        byte[] result = new byte[HASH_LENGTH];
        generate.generateBytes(password.getBytes(StandardCharsets.UTF_8), result, 0, result.length);

        return new HashedPassword(result, salt);
    }

    private byte[] generateSalt16Byte() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);

        return salt;
    }

    @Override
    public HashedPassword encodePassword(String password) {
        byte[] salt = generateSalt16Byte();

        return encrypt(password, salt);
    }

    @Override
    public boolean validatePassword(String passwordInPlainText, HashedPassword storedPassword) {
        HashedPassword newlyHashed = encrypt(passwordInPlainText, storedPassword.getSalt());

        return Arrays.equals(storedPassword.getHash(), newlyHashed.getHash());
    }
}
