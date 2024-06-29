package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.ResourceCategory;
import universityconnect.dto.ResourceCategoryDTO;

@Mapper
public interface ResourceCategoryMapper {
    ResourceCategoryMapper INSTANCE = Mappers.getMapper(ResourceCategoryMapper.class);

    ResourceCategoryDTO resourceCategoryToResourceCategoryDTO(ResourceCategory resourceCategory);

    ResourceCategory resourceCategoryDTOToResourceCategory(ResourceCategoryDTO resourceCategoryDTO);
}

