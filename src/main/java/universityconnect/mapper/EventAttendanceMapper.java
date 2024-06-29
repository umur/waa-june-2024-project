package universityconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import universityconnect.domain.EventAttendance;
import universityconnect.dto.EventAttendanceDTO;

@Mapper
public interface EventAttendanceMapper {
    EventAttendanceMapper INSTANCE = Mappers.getMapper(EventAttendanceMapper.class);

    @Mapping(target = "studentId", source = "student.id")
    EventAttendanceDTO eventAttendanceToEventAttendanceDTO(EventAttendance eventAttendance);

    @Mapping(target = "student", ignore = true)
    EventAttendance eventAttendanceDTOToEventAttendance(EventAttendanceDTO eventAttendanceDTO);
}

