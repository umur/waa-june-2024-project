package com.waa.project.util;

public class FileSaveLocations {

    public static String studentProfile(String username) {
        return "students/" + username + "/profile";
    }

    public static String academicResource(String resType) {
        return resType;
    }
}
