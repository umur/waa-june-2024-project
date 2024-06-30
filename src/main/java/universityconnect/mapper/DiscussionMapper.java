package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Discussion;
import universityconnect.domain.DiscussionThread;
import universityconnect.dto.DiscussionDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface DiscussionMapper {

    DiscussionMapper INSTANCE = Mappers.getMapper(DiscussionMapper.class);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "discussionThreadIds", source = "discussionThreads")
    @Mapping(target = "userId", source = "user.id")
    DiscussionDTO discussionToDiscussionDTO(Discussion discussion);

    @Mapping(target = "category", ignore = true)
    @Mapping(target = "discussionThreads", ignore = true)
    @Mapping(target = "user", ignore = true)
    Discussion discussionDTOToDiscussion(DiscussionDTO discussionDTO);

    default List<Long> mapDiscussionThreadIds(List<DiscussionThread> discussionThreads) {
        return discussionThreads.stream()
                .map(DiscussionThread::getId)
                .collect(Collectors.toList());
    }
}

