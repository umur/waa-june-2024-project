package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import universityconnect.domain.Resource;
import universityconnect.domain.ResourceCategory;
import universityconnect.dto.ResourceDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.ResourceMapper;
import universityconnect.repository.ResourceCategoryRepository;
import universityconnect.repository.ResourceRepository;
import universityconnect.service.ResourceService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceRepository resourceRepository;
    private final ResourceMapper resourceMapper;
    private final ResourceCategoryRepository resourceCategoryRepository;
    @Override
    public ResourceDTO createResource(ResourceDTO resourceDTO) {
        Resource resource = resourceMapper.resourceDTOToResource(resourceDTO);

        if(resourceDTO.getResourceCategoryId() != null){
            ResourceCategory resourceCategory = resourceCategoryRepository.findById(resourceDTO.getResourceCategoryId())
                    .orElseThrow(()-> new ResourceNotFoundException("Resource Category is not found"));
            resource.setResourceCategory(resourceCategory);
        }
        Resource createdResource = resourceRepository.save(resource);
        return resourceMapper.resourceToResourceDTO(createdResource);
    }

    @Override
    public List<ResourceDTO> getAllResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream()
                .map(resourceMapper::resourceToResourceDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResourceDTO getResourceById(long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource Id is not found, id: " + id));
        return resourceMapper.resourceToResourceDTO(resource);
    }

    @Override
    public ResourceDTO updateResource(long id, ResourceDTO resourceDTO) {
        Resource existingResource = resourceRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource Id is not found, id: " + id));

        if(resourceDTO.getResourceCategoryId() != null){
            ResourceCategory resourceCategory = resourceCategoryRepository.findById(resourceDTO.getResourceCategoryId())
                    .orElseThrow(()->new ResourceNotFoundException("ResourceCategory Id is not found, id: " + id));
            existingResource.setResourceCategory(resourceCategory);
        }

        existingResource.setFilePath(resourceDTO.getFilePath());
        existingResource.setFileName(resourceDTO.getFileName());
        Resource resource = resourceRepository.save(existingResource);
        return resourceMapper.resourceToResourceDTO(resource);
    }

    @Override
    public void deleteResource(long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource Id is not found, id: " + id));
        resourceRepository.delete(resource);
    }
}
