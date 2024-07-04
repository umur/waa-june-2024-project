package com.waa.project.security.util;

public class AuthErrorMessages {
    private static final String INVALID_CREDENTIALS = "Invalid credentials";
    private static final String INVALID_TOKEN       = "Invalid Token";
    private static final String FORBIDDEN           = "You cannot perform this action.";

    private AuthErrorMessages() {
    }

    public static String invalidToken() {
        return INVALID_TOKEN;
    }

    public static String forbidden() {
        return FORBIDDEN;
    }
}
