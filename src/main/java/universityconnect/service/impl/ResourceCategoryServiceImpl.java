package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.ResourceCategory;
import universityconnect.dto.ResourceCategoryDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.ResourceCategoryMapper;
import universityconnect.repository.ResourceCategoryRepository;
import universityconnect.service.ResourceCategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    private final ResourceCategoryRepository resourceCategoryRepository;
    private final ResourceCategoryMapper resourceCategoryMapper;
    @Override
    public ResourceCategoryDTO createResourceCategory(ResourceCategoryDTO resourceCategoryDTO) {
        ResourceCategory resourceCategory = resourceCategoryMapper.resourceCategoryDTOToResourceCategory(resourceCategoryDTO);
        ResourceCategory createdResourceCategory = resourceCategoryRepository.save(resourceCategory);
        return resourceCategoryMapper.resourceCategoryToResourceCategoryDTO(createdResourceCategory);
    }

    @Override
    public List<ResourceCategoryDTO> getAllResourceCategories() {
        List<ResourceCategory> resourceCategories = resourceCategoryRepository.findAll();
        return resourceCategories.stream()
                .map(resourceCategoryMapper::resourceCategoryToResourceCategoryDTO)
                .toList();
    }

    @Override
    public ResourceCategoryDTO getResourceCategoryById(long id) {
        ResourceCategory resourceCategory = resourceCategoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource Category Id is not found, id: " + id));
        return resourceCategoryMapper.resourceCategoryToResourceCategoryDTO(resourceCategory);
    }

    @Override
    public ResourceCategoryDTO updateResourceCategory(long id, ResourceCategoryDTO resourceCategoryDTO) {
        ResourceCategory resourceCategory = resourceCategoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource Category Id is not found, id: " + id));

        resourceCategory.setName(resourceCategoryDTO.getName());
        ResourceCategory r = resourceCategoryRepository.save(resourceCategory);
        return resourceCategoryMapper.resourceCategoryToResourceCategoryDTO(r);
    }

    @Override
    public void deleteResourceCategory(long id) {
        ResourceCategory resourceCategory = resourceCategoryRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Resource Category Id is not found, id: " + id));
        resourceCategoryRepository.delete(resourceCategory);
    }
}
