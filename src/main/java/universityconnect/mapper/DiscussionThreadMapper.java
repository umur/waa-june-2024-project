package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.DiscussionThread;
import universityconnect.dto.DiscussionThreadDTO;

@Mapper
public interface DiscussionThreadMapper {
    DiscussionThreadMapper INSTANCE = Mappers.getMapper(DiscussionThreadMapper.class);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "discussionId", source = "discussion.id")
    @Mapping(target = "nestedThreadIds", source = "nestedThreads.id")
    DiscussionThreadDTO discussionThreadToDiscussionThreadDTO(DiscussionThread discussionThread);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "discussion", ignore = true)
    @Mapping(target = "nestedThreads", ignore = true)
    DiscussionThread discussionThreadDTOToDiscussionThread(DiscussionThreadDTO discussionThreadDTO);
}

