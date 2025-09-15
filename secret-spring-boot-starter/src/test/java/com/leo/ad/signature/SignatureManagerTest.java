package com.leo.ad.signature;

import org.junit.jupiter.api.Test;


class SignatureManagerTest {

    private static final String API_KEY = "one-net-x1";
    private static final String SECRET = "tq0eruNtp5jjndItd33RL+yb3866Obez60YrvatnT1U";

    @Test
    void generateSignature() {
        String method = "GET";
        String path = "/game/demo/testSecret";
         long timestamp = System.currentTimeMillis();
//        long timestamp = 1757326120262L;
        // 应该每次请求都不同，这里为了方便测试，使用固定的随机字符串
        String nonce = "random_nonce_123457";
        String requestBody ="";
        // 51af58c7a4e2f7a28670e6aca777d76540baefbd868faf6c2167958e0ac9a9b1
//        String requestBody =
//                "{\"aid\":\"aid_998155075037\",\"gaid\":\"gaid_3c15828d3051\",\"pkg\":\"com.merge\",\"pvc\":\"1\",\"svc\":\"1\",\"pvn\":\"1\",\"mobileBrand\":\"mobileBrand_5ce306da563d\",\"mobileModel\":\"mobileModel_454017b7f730\"}";
        String signature = SignatureManager.generateSignature(method, path, timestamp, nonce, requestBody, SECRET);
        // 输出请求头示例
        System.out.println("\n请求头应包含:");
        System.out.println("X-Auth-Api-Key: " + API_KEY);
        System.out.println("X-Auth-Timestamp: " + timestamp);
        System.out.println("X-Auth-Nonce: " + nonce);
        System.out.println("X-Auth-Signature: " + signature);
    }

}
