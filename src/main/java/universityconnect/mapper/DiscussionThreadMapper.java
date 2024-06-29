package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.DiscussionThread;
import universityconnect.dto.DiscussionThreadDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface DiscussionThreadMapper {
    DiscussionThreadMapper INSTANCE = Mappers.getMapper(DiscussionThreadMapper.class);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "discussionId", source = "discussion.id")
    @Mapping(target = "nestedThreadIds", expression = "java(mapNestedThreadIds(discussionThread.getNestedThreads()))")
    DiscussionThreadDTO discussionThreadToDiscussionThreadDTO(DiscussionThread discussionThread);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "discussion", ignore = true)
    @Mapping(target = "nestedThreads", ignore = true)
    DiscussionThread discussionThreadDTOToDiscussionThread(DiscussionThreadDTO discussionThreadDTO);

    default List<Long> mapNestedThreadIds(List<DiscussionThread> nestedThreads) {
        if (nestedThreads == null) {
            return null;
        }
        return nestedThreads.stream()
                .map(DiscussionThread::getId)
                .collect(Collectors.toList());
    }
}


