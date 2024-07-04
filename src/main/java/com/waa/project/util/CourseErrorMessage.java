package com.waa.project.util;

public class CourseErrorMessage {
    private CourseErrorMessage() {}

    public static String courseNotFound(Long courseId) {
        return "Course with id #" + courseId + " not found.";
    }
}
