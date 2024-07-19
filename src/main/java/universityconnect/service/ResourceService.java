package universityconnect.service;


import universityconnect.dto.ResourceDTO;

import java.util.List;

public interface ResourceService {

    ResourceDTO createResource(ResourceDTO resourceDTO);

    List<ResourceDTO> getAllResources();

    ResourceDTO getResourceById(long id);

    ResourceDTO updateResource(long id,ResourceDTO resourceDTO);

    void deleteResource(long id);
}
