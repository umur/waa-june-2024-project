package com.waa.project.util;

public class UserErrorMessages {

    private static final String INCORRECT_PASSWORD  = "Current password is incorrect.";
    private static final String TOKEN_EXPIRED       = "Token is expired.";
    private static final String INVALID_CREDENTIALS = "Invalid username or password.";

    private UserErrorMessages() {
    }

    public static String userNotFound(Long userId) {
        return "User with id #" + userId + " not found.";
    }

    public static String userNotFound(String username) {
        return "User with username #" + username + " not found.";
    }

    public static String emailAlreadyExists(String email) {
        return "User with email " + email + " already exists.";
    }

    public static String currentPasswordIsIncorrect() {
        return INCORRECT_PASSWORD;
    }

    public static String invalidCredentials() {
        return INVALID_CREDENTIALS;
    }

    public static String tokenExpired() {
        return TOKEN_EXPIRED;
    }
}
