package com.waa.project.service.impl;

import com.waa.project.exception.ResourceNotFoundException;
import com.waa.project.repository.FileRepository;
import com.waa.project.service.FileService;
import com.waa.project.util.FileErrorMessages;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService {
    private static final String BASE_PATH = "";

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public String saveFile(MultipartFile multipartFile) {
        return saveFile(multipartFile, BASE_PATH);
    }

    @Override
    public String saveFile(MultipartFile multipartFile, String saveLocation) {
        // Convert MultipartFile to File
        File file = convertMultipartFileToFile(multipartFile);

        // Save the file using the repository
        return fileRepository.saveFile(file, BASE_PATH + saveLocation);
    }

    @Override
    public File getFile(String filePath) {
        Optional<File> fileOptional = fileRepository.getFile(filePath);
        return fileOptional.orElseThrow(() -> new ResourceNotFoundException(FileErrorMessages.fileNotFound()));
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(convFile);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return convFile;
    }
}
