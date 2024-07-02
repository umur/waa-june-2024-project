package universityconnect.service;

import universityconnect.dto.ResourceCategoryDTO;

import java.util.List;

public interface ResourceCategoryService {

    ResourceCategoryDTO createResourceCategory(ResourceCategoryDTO resourceCategoryDTO);

    List<ResourceCategoryDTO> getAllResourceCategories();

    ResourceCategoryDTO getResourceCategoryById(long id);

    ResourceCategoryDTO updateResourceCategory(long id, ResourceCategoryDTO resourceCategoryDTO);

    void deleteResourceCategory(long id);

}
