package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Resource;
import universityconnect.dto.ResourceDTO;

@Mapper
public interface ResourceMapper {
    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    @Mapping(target = "resourceCategoryId", source = "resourceCategory.id")
    ResourceDTO resourceToResourceDTO(Resource resource);

    @Mapping(target = "resourceCategory", ignore = true)
    Resource resourceDTOToResource(ResourceDTO resourceDTO);
}

