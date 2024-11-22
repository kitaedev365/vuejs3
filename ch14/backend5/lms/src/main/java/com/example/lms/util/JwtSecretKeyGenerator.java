package com.example.lms.util;

import java.util.Base64;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtSecretKeyGenerator {
    public static void main(String[] args) {
        // HS512 알고리즘에 적합한 512비트(64바이트) 키 생성
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);
        
        System.out.println("Generated JWT Secret Key: " + secretKey);
    }
}