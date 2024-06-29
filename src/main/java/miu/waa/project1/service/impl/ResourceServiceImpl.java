package miu.waa.project1.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import miu.waa.project1.model.Resource;
import miu.waa.project1.repository.ResourceRepository;
import miu.waa.project1.service.ResourceService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Setter
@Getter
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;

    @Override
    public List<Resource> getAllResources(Pageable pageable) {
        return resourceRepository.findAll(pageable).getContent();
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
            return resourceRepository.save(r);

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
}
