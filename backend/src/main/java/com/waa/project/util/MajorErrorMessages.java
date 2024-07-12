package com.waa.project.util;

public class MajorErrorMessages {
    private MajorErrorMessages() {
    }

    public static String majorrNotFound(Long major) {
        return "Major with id #" + major + " not found.";
    }
}