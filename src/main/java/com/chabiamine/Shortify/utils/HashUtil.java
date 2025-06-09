package com.chabiamine.Shortify.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HexFormat;

@Component
public class HashUtil {


        private static final int SHORT_CODE_LENGTH = 8;

        public String generateShortCode(String longUrl, String username) throws NoSuchAlgorithmException {

            String input = longUrl + username + System.currentTimeMillis();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            System.out.println(HexFormat.of().formatHex(hashBytes).substring(0, SHORT_CODE_LENGTH));
            return HexFormat.of().formatHex(hashBytes).substring(0, SHORT_CODE_LENGTH);
        }

        public String generateShortCodeAlternative(String longUrl, String username)
                throws NoSuchAlgorithmException {

            String input = longUrl + username + Instant.now().toEpochMilli();
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            return HexFormat.of().formatHex(hashBytes).substring(0, SHORT_CODE_LENGTH);
        }
    }

