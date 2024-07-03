package edu.university_connect.mapper;

import edu.university_connect.domain.entity.discussionthread.Reply;
import edu.university_connect.model.contract.dto.ReplyDto;
import edu.university_connect.model.contract.request.discussionthread.ReplyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReplyDtoMapper {
    ReplyDtoMapper MAPPER =
            Mappers.getMapper(ReplyDtoMapper.class);

    Reply dtoToEntity(ReplyRequest request);

    ReplyDto entityToDto(Reply reply);
}
