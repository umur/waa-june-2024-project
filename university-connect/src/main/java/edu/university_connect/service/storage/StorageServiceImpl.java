package edu.university_connect.service.storage;


import edu.university_connect.exception.ServiceException;
import edu.university_connect.model.enums.AppStatusCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    @Value("${file.upload.path}")
    private String location;

    @Value("${app.allowed-file-types}")
    private String allowedFileTypes;

    public String[] allowedFileTypes() {
        return allowedFileTypes.split(",");
    }

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
    public Stream<Path> loadAll(String username, String resourceType, Long resourceId) {
        Path rootLocation = getRootLocation(username,resourceType,resourceId);
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
    public Path load(String filename, Path rootLocation) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename,Path rootLocation) {
        try {
            Path file = load(filename,rootLocation);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw ServiceException.of(AppStatusCode.E50009);

            }
        }
        catch (MalformedURLException e) {
            throw ServiceException.of(AppStatusCode.E50009);
        }
    }
    @Override
    public List<Resource> loadAsResource(String username, String resourceType, Long resourceId) {
        Path rootLocation = getRootLocation(username,resourceType,resourceId);
        List<Path> paths=loadAll(username,resourceType,resourceId).toList();
        return paths.stream().map(x->loadAsResource(x.toString(),rootLocation)).toList();
     }

    @Override
    public List<String> loadFileNames(String username, String resourceType, Long resourceId) {
        return loadAll(username,resourceType,resourceId).map(Path::toString).toList();
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
        return rootLocation.toString().replace(location,"");
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

    @Override
    public Path getRootLocation(String username, String resourceType, Long resourceId){
        return Paths.get(location+"/"+resourceType+"/"+username+"/"+resourceId);
    }

    @Override
    public boolean validateFileTypes(MultipartFile[] files) {
        for (MultipartFile file : files) {
            if (!isValidFileType(file, allowedFileTypes())) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidFileType(MultipartFile file, String[] allowedFileTypes) {
        for (String type : allowedFileTypes) {
            if (type.trim().equalsIgnoreCase(file.getContentType())) {
                return true;
            }
        }
        return false;
    }
}