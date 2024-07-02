package edu.university_connect.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;
public interface StorageService {

    void store(MultipartFile file, Path rootLocation);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    String store(MultipartFile[] files, String username, String resourceType, Long id);

    String store(MultipartFile file, String username, String resourceType, Long id);
}