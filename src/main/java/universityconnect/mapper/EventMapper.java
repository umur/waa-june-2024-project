package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Event;
import universityconnect.dto.EventDTO;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "eventAttendanceIds", source = "eventAttendances.id")
    @Mapping(target = "organizerId", source = "organizer.id")
    EventDTO eventToEventDTO(Event event);

    @Mapping(target = "eventAttendances", ignore = true)
    @Mapping(target = "organizer", ignore = true)
    Event eventDTOToEvent(EventDTO eventDTO);
}

