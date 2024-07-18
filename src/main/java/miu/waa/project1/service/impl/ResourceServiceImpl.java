package miu.waa.project1.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import miu.waa.project1.model.Resource;
import miu.waa.project1.model.User;
import miu.waa.project1.repository.ResourceRepository;
import miu.waa.project1.service.ResourceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
@Setter
@Getter
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final UserServiceImpl userService;

    @Override
    public Page<Resource> getAllResources(Pageable pageable) {
        return resourceRepository.findAll(pageable);
    }

    @Override
    public Resource getResourceById(Long id) {
        return resourceRepository.findById(id).orElse(null);
    }

    @Override
    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    @Override
    public Resource updateResource(Long id, Resource resource) {
        Resource r = resourceRepository.findById(id).orElse(null);
        if (r != null) {
            r.setName(resource.getName());
            r.setDescription(resource.getDescription());
            r.setUrl(resource.getUrl());
            r.setType(resource.getType());
            return resourceRepository.save(r);//
        }
        return null;
    }

    @Override
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

    @Override
    public List<Resource> getResourcesByUserId(Long userId) {
        return resourceRepository.findAllByUserId(userId);
    }

    @Override
    public Resource upload(MultipartFile file, String desc) {
        String fileName = generateFileName(file.getOriginalFilename());
        String property = System.getProperty("file.separator");
        System.out.println(fileName);
        String directoryPath = ResourceServiceImpl.getClassDirectoryPath(ResourceServiceImpl.class);
        String directory = directoryPath + property + "resources" + property;
        if (!new File(directory).exists()) {
            new File(directory).mkdir();
        }
        String filePath = directory + fileName;
        Resource newImage;
        File uploadedFile = new File(filePath);

        try (FileOutputStream fos = new FileOutputStream(uploadedFile)) {
            fos.write(file.getBytes());
            Resource image = new Resource();
            image.setSize(file.getSize());
            image.setType(file.getContentType());
            image.setUrl("http://localhost:8080/resources/" + fileName);
            image.setDescription(desc);
            image.setName(fileName);
            System.out.println(image.toString());

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
                throw new RuntimeException("Invalid token or user not found");
            }
            User user = userService.getUserByEmail(userDetails.getUsername());
            image.setUser(user);
            newImage = resourceRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newImage;
    }

    private String generateFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        return UUID.randomUUID().toString() + extension;
    }

    public static String getClassDirectoryPath(Class<?> clazz) {
        URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
        String filePath = url.getFile();
        try {
            filePath = java.net.URLDecoder.decode(filePath, "UTF-8");
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new File(filePath).getParent();
    }
}
