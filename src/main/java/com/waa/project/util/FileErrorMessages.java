package com.waa.project.util;

public class FileErrorMessages {

    private static final String FILE_NOT_FOUND = "File not found";

    private FileErrorMessages() {
    }

    public static String fileNotFound() {
        return FILE_NOT_FOUND;
    }
}
