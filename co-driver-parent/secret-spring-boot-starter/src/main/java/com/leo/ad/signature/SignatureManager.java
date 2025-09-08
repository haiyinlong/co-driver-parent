package com.leo.ad.signature;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * SignatureManager
 *
 * @author HaiYinLong
 * @version 2025/09/08 14:10
 **/
public class SignatureManager {
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    public static final String SHA_256 = "SHA-256";

    /**
     * 生成签名
     *
     * @param method POST GET PUT DELETE
     * @param path 请求路径
     * @param timestamp 时间戳
     * @param secret 密钥
     * @return 签名
     */
    public static String generateSignature(String method, String path, long timestamp, String secret) {
        return generateSignature(method, path, timestamp, null, null, secret);
    }

    /**
     * 生成签名
     *
     * @param method POST GET PUT DELETE
     * @param path 请求路径
     * @param timestamp 时间戳
     * @param requestBody 请求体
     * @param secret 密钥
     * @return 签名
     */
    public static String generateSignature(String method, String path, long timestamp, String requestBody,
        String secret) {
        return generateSignature(method, path, timestamp, null, requestBody, secret);
    }

    /**
     * 生成签名
     *
     * @param method POST GET PUT DELETE
     * @param path 请求路径
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param requestBody 请求体
     * @param secret 密钥
     * @return 签名
     */
    public static String generateSignature(String method, String path, long timestamp, String nonce, String requestBody,
        String secret) {
        try {
            // 计算请求体的SHA-256哈希
            String bodyHash = calculateBodyHash(requestBody);
            // 构造签名内容
            String payload = method + path + timestamp + nonce + bodyHash;
            // 生成HMAC签名
            return generateHmac(payload, secret);
        } catch (Exception e) {
            throw new RuntimeException("生成签名失败", e);
        }
    }

    /**
     * 计算Body的SHA-256哈希
     */
    public static String calculateBodyHash(String requestBody) throws NoSuchAlgorithmException {
        String bodyHash = "";
        if (requestBody != null && !requestBody.isEmpty()) {
            MessageDigest digest = MessageDigest.getInstance(SHA_256);
            byte[] hashBytes = digest.digest(requestBody.getBytes(StandardCharsets.UTF_8));
            bodyHash = bytesToHex(hashBytes);
        }
        return bodyHash;
    }

    /**
     * 计算Body的SHA-256哈希
     */
    public static String calculateBodyHash(byte[] requestBody) throws NoSuchAlgorithmException {
        String bodyHash = "";
        if (requestBody != null && requestBody.length > 0) {
            MessageDigest digest = MessageDigest.getInstance(SHA_256);
            byte[] hashBytes = digest.digest(requestBody);
            bodyHash = bytesToHex(hashBytes);
        }
        return bodyHash;
    }

    /**
     * 生成 HMAC 签名
     */
    private static String generateHmac(String data, String secret)
        throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(HMAC_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_ALGORITHM);
        mac.init(secretKeySpec);
        byte[] hmacBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hmacBytes);
    }

    /**
     * 将字节数组转换为十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
