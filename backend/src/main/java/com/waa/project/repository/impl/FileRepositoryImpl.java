package com.waa.project.repository.impl;

import com.waa.project.repository.FileRepository;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Component
public class FileRepositoryImpl implements FileRepository {

    private static final String BASE_PATH   = "uploads";
    private static final String DATE_FORMAT = "yyyyMMddhhmmssSSS";

    @Override
    public String saveFile(File file) {
        return saveFile(file, getUploadsDirectory().toString());
    }

    @Override
    public String saveFile(File file, String saveLocation) {
        try {
            Path uploadsDirPath = Paths.get(getUploadsDirectory().toString(), saveLocation);


            System.out.println(getUploadsDirectory().toAbsolutePath());
            System.out.println(uploadsDirPath);

            if (!Files.exists(uploadsDirPath)) {
                Files.createDirectories(uploadsDirPath);
            }

            String sanitizedFilename = sanitizeFilename(file.getName());
            String uniqueFilename    = generateUniqueFilename(sanitizedFilename);
            Path   filePath          = uploadsDirPath.resolve(uniqueFilename).normalize();
            Files.copy(file.toPath(), filePath);

            return filePath.toString().substring(System.getProperty("user.dir").length());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<File> getFile(String filePath) {
        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (Files.exists(path) && Files.isRegularFile(path)) {
            return Optional.of(path.toFile());
        } else {
            return Optional.empty();
        }
    }

    private Path getUploadsDirectory() {
        String userDir = System.getProperty("user.dir");

        return Paths.get(userDir, BASE_PATH);
    }

    private String sanitizeFilename(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }

    private String generateUniqueFilename(String filename) {
        String timestamp = new SimpleDateFormat(DATE_FORMAT).format(new Date());
        String extension = "";

        int extensionIndex = filename.lastIndexOf('.');
        if (extensionIndex > 0) {
            extension = filename.substring(extensionIndex);
            filename  = filename.substring(0, extensionIndex);
        }

        return filename + "_" + timestamp + extension;
    }
}
