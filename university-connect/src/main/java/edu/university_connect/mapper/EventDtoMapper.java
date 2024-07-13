package edu.university_connect.mapper;

import edu.university_connect.domain.entity.Event;
import edu.university_connect.model.contract.dto.EventDto;
import edu.university_connect.model.contract.request.event.EventRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EventDtoMapper {
    EventDtoMapper MAPPER =
            Mappers.getMapper(EventDtoMapper.class);

    Event dtoToEntity(EventRequest request);

    EventDto entityToDto(Event event);
}
