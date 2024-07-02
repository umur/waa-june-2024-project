package edu.university_connect.mapper;

import edu.university_connect.domain.entity.discussionthread.Post;
import edu.university_connect.model.contract.dto.PostDto;
import edu.university_connect.model.contract.request.discussionthread.PostRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostDtoMapper {
    PostDtoMapper MAPPER =
            Mappers.getMapper(PostDtoMapper.class);

    Post dtoToEntity(PostRequest request);

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    PostDto entityToDto(Post post);
}
