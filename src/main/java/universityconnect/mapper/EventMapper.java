package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.Event;
import universityconnect.domain.EventAttendance;
import universityconnect.dto.EventDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    @Mapping(target = "eventAttendanceIds", source = "eventAttendances")
    @Mapping(target = "organizerId", source = "organizer.id")
    @Mapping(target = "organizerName",source = "organizer.username")
    EventDTO eventToEventDTO(Event event);

    @Mapping(target = "eventAttendances", ignore = true)
    @Mapping(target = "organizer", ignore = true)
    Event eventDTOToEvent(EventDTO eventDTO);

    default List<Long> mapEventAttendanceIds(List<EventAttendance> eventAttendances) {
        return eventAttendances.stream()
                .map(EventAttendance::getId)
                .collect(Collectors.toList());
    }
}

