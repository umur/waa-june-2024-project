package edu.university_connect.service.storage;


import edu.university_connect.exception.ServiceException;
import edu.university_connect.model.enums.AppStatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${file.upload.path}")
    private String location;

    @Override
    public void store(MultipartFile file, Path rootLocation) {
        try {
            if (file.isEmpty()) {
                throw ServiceException.of(AppStatusCode.E50007);
            }
            Path destinationFile = rootLocation.resolve(
                            Paths.get(Objects.requireNonNull(file.getOriginalFilename())))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw ServiceException.of(AppStatusCode.E50007);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw ServiceException.of(AppStatusCode.E50007);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        Path rootLocation = Paths.get(location);
        try {
            return Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(rootLocation::relativize);
        }
        catch (IOException e) {
            throw ServiceException.of(AppStatusCode.E50008);
        }

    }

    @Override
    public Path load(String filename) {
        Path rootLocation = Paths.get(location);
        rootLocation = Paths.get(location);
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw ServiceException.of(AppStatusCode.E50008);

            }
        }
        catch (MalformedURLException e) {
            throw ServiceException.of(AppStatusCode.E50008);
        }
    }

    @Override
    public String store(MultipartFile[] files, String username, String resourceType, Long resourceId) {
        Path rootLocation = getRootLocation(username,resourceType,resourceId);
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw ServiceException.of(AppStatusCode.E50008);
        }
        for (MultipartFile file : files) {
            store(file,rootLocation);
        }
        return rootLocation.toString();
    }

    @Override
    public String store(MultipartFile file, String username, String resourceType, Long resourceId) {
        Path rootLocation = getRootLocation(username,resourceType,resourceId);
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw ServiceException.of(AppStatusCode.E50008);
        }
        store(file,rootLocation);
        return rootLocation.toString();
    }

    public Path getRootLocation(String username, String resourceType, Long resourceId){
        return Paths.get(location+"/"+resourceType+"/"+username+"/"+resourceId);
    }
}