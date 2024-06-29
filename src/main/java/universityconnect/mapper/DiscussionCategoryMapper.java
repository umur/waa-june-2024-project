package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.DiscussionCategory;
import universityconnect.dto.DiscussionCategoryDTO;

@Mapper
public interface DiscussionCategoryMapper {
    DiscussionCategoryMapper INSTANCE = Mappers.getMapper(DiscussionCategoryMapper.class);

    DiscussionCategoryDTO discussionCategoryToDiscussionCategoryDTO(DiscussionCategory discussionCategory);

    DiscussionCategory discussionCategoryDTOToDiscussionCategory(DiscussionCategoryDTO discussionCategoryDTO);
}
