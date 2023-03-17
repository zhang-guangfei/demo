package com.neo.health.common.utils;

public class UUIDGenerator {

    public static final String PREFIX_ROLE = "ROLE";
    public static final String PREFIX_USER = "USER";
    public static final String PREFIX_PERMISSION = "PERMISSION";
    public static final String PREFIX_EVENT = "EVENT";

    public static String roleId() {
        return generate(PREFIX_ROLE);
    }

    public static String userId() {
        return generate(PREFIX_USER);
    }

    public static String permissionId() {
        return generate(PREFIX_PERMISSION);
    }

    public static String eventId() {
        return generate(PREFIX_EVENT);
    }


    private static String generate(String prefix) {
        return String.format("%s%s", prefix, java.util.UUID.randomUUID().toString().toUpperCase());
    }


}
