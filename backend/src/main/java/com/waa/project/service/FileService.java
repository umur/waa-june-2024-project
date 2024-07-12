package com.waa.project.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    String saveFile(MultipartFile multipartFile);

    String saveFile(MultipartFile multipartFile, String saveLocation);

    File getFile(String filePath);
}
