package edu.university_connect.service.resource;

import edu.university_connect.model.contract.dto.ResourceDto;
import edu.university_connect.model.contract.request.resource.ResourceRequest;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResourceService {
    List<ResourceDto> getAll();
    ResourceDto getById(Long id);

    ResourceDto create(ResourceRequest createRequest);

    ResourceDto update(Long id, ResourceRequest updateRequest);

    boolean delete(Long id);


    Page<ResourceDto> getPage(Pageable pageable);

    ResourceDto handleFileUpload(MultipartFile[] files, Long id);

    Resource loadResource(String filename, Long id);

    Page<ResourceDto> getUserResourcePage(Long id, Pageable pageable);
}
