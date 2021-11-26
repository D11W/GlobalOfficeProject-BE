package com.bluescripts.globaloffice.office.utils;

import java.util.UUID;

public class IdUtils {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateId(String prefix) {

        return prefix + String.valueOf(Math.abs(UUID.randomUUID().getLeastSignificantBits())).substring(10, 15) +
                randomAlphaNumeric(4);
    }

    private static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
