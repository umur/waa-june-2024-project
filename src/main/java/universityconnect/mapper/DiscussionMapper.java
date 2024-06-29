package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Discussion;
import universityconnect.dto.DiscussionDTO;

@Mapper
public interface DiscussionMapper {
    DiscussionMapper INSTANCE = Mappers.getMapper(DiscussionMapper.class);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "discussionThreadIds", source = "discussionThreads.id")
    @Mapping(target = "userId", source = "user.id")
    DiscussionDTO discussionToDiscussionDTO(Discussion discussion);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "discussionThreads", ignore = true)
    @Mapping(target = "user", ignore = true)
    Discussion discussionDTOToDiscussion(DiscussionDTO discussionDTO);
}
