package universityconnect.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import universityconnect.config.StorageProperties;
import universityconnect.dto.ResourceDTO;
import universityconnect.exception.StorageException;
import universityconnect.exception.StorageFileNotFoundException;
import universityconnect.service.ResourceService;
import universityconnect.service.StorageService;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;
    @Autowired
    private  ResourceService resourceService;

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {

        if(properties.getLocation().trim().length() == 0){
            throw new StorageException("File upload location can not be Empty.");
        }

        this.rootLocation = Paths.get(properties.getLocation());
    }

    @Override
    public void store(String path,MultipartFile file,Long categoryId) {
        try {
            if(path.isEmpty()){
                throw new StorageException("File path is empty.");
            }
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            // Resolve the custom path against the root location and normalize it
            Path destinationPath = this.rootLocation.resolve(Paths.get(path)).normalize();
            Path destinationFile = destinationPath.resolve(Paths.get(file.getOriginalFilename())).normalize().toAbsolutePath();

            if (!destinationFile.startsWith(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                // Ensure the directories exist
                Files.createDirectories(destinationFile.getParent());
                // Copy the file to the target location
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

                resourceService.createResource(new ResourceDTO(file.getOriginalFilename(), destinationPath.toString(),categoryId));
            }
        }
        catch (IOException e) {

            throw new StorageException("Failed to store file."+e.toString());
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }

    }

    @Override
    public Path load(String filename) {
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
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}