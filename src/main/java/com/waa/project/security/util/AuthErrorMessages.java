package com.waa.project.security.util;

public class AuthErrorMessages {
    private static final String INVALID_CREDENTIALS = "Invalid credentials";
    private static final String INVALID_TOKEN       = "Invalid Token";

    private AuthErrorMessages() {
    }

    public static String invalidToken() {
        return INVALID_TOKEN;
    }
}
