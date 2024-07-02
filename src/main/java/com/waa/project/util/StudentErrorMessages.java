package com.waa.project.util;

public class StudentErrorMessages {
    private StudentErrorMessages() {
    }

    public static String studentNotFound(Long studentId) {
        return "Student with id #" + studentId + " not found.";
    }
}
