package com.waa.project.repository;

import java.io.File;
import java.util.Optional;

public interface FileRepository {
    String saveFile(File file);

    String saveFile(File file, String saveLocation);

    Optional<File> getFile(String filePath);
}
