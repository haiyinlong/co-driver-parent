package com.leo.ad.signature;

import java.security.SecureRandom;
import java.util.Base64;

/**
 * SecretKeyGenerator
 *
 * @author HaiYinLong
 * @version 2025/09/08 15:05
 **/
public class SecretKeyGenerator {
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final Base64.Encoder ENCODER = Base64.getEncoder().withoutPadding();
    private static final int DEFAULT_BYTE_LENGTH = 32;

    public static String generateSecretKey(int byteLength) {
        byte[] bytes = new byte[byteLength];
        SECURE_RANDOM.nextBytes(bytes);
        return ENCODER.encodeToString(bytes);
    }

    public static String generateSecretKey() {
        return generateSecretKey(DEFAULT_BYTE_LENGTH);
    }

}
