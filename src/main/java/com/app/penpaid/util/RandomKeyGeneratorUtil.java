package com.app.penpaid.util;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RandomKeyGeneratorUtil {
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateRandomKey() {
        // Seed the random generator with the current date and time
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        RANDOM.setSeed(dateTime.getBytes());

        // Generate a random 6-digit alphanumeric key
        StringBuilder key = new StringBuilder(6);
        for (int i = 0; i < 15; i++) {
            key.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }
        return key.toString();
    }
}
