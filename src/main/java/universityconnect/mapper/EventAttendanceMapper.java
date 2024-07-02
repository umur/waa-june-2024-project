package universityconnect.mapper;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.EventAttendance;
import universityconnect.dto.EventAttendanceDTO;

@Mapper(componentModel = "spring")
public interface EventAttendanceMapper {
    EventAttendanceMapper INSTANCE = Mappers.getMapper(EventAttendanceMapper.class);

    @Mapping(target = "studentId", source = "student.id")
    @Mapping(target = "eventId", source = "event.id")
    EventAttendanceDTO eventAttendanceToEventAttendanceDTO(EventAttendance eventAttendance);

    @Mapping(target = "student", ignore = true)
    @Mapping(target = "event",ignore = true)
    EventAttendance eventAttendanceDTOToEventAttendance(EventAttendanceDTO eventAttendanceDTO);
}

