package com.leo.ad.signature;

import org.junit.jupiter.api.Test;

class SecretKeyGeneratorTest {
    @Test
    void generateSecretKey() {
        // 生成 32 字节（256 位）密钥
        String secretKey = SecretKeyGenerator.generateSecretKey();
        System.out.println("Secret Key: " + secretKey);
    }
}
