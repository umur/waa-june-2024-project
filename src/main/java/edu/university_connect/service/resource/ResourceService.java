package edu.university_connect.service.resource;

import edu.university_connect.model.contract.dto.ResourceDto;
import edu.university_connect.model.contract.request.resource.ResourceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResourceService {
    List<ResourceDto> getAll();
    ResourceDto getById(Long id);

    ResourceDto create(ResourceRequest createRequest);

    ResourceDto update(Long id, ResourceRequest updateRequest);

    boolean delete(Long id);


    Page<ResourceDto> getPage(Pageable pageable);
}
