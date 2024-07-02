package edu.university_connect.service.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
public interface StorageService {

    void store(MultipartFile file, Path rootLocation);


    Stream<Path> loadAll(String username, String resourceType, Long resourceId);

    Path load(String filename, Path rootLocation);

    Resource loadAsResource(String filename, Path rootLocation);

    List<Resource> loadAsResource(String username, String resourceType, Long resourceId);

    List<String> loadFileNames(String username, String resourceType, Long resourceId);

    String store(MultipartFile[] files, String username, String resourceType, Long id);

    String store(MultipartFile file, String username, String resourceType, Long id);

    Path getRootLocation(String username, String resourceType, Long resourceId);

    boolean validateFileTypes(MultipartFile[] files);
}