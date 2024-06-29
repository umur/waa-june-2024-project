package miu.waa.project1.service;

import miu.waa.project1.common.ResourceType;
import miu.waa.project1.model.Resource;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ResourceService {
    List<Resource> getAllResources(Pageable p);
    Resource getResourceById(Long id);
    Resource createResource(Resource resource);
    Resource updateResource(Long id, Resource resource);
    void deleteResource(Long id);
    List<Resource> getResourcesByUserId(Long userId);
}
