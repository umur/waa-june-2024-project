package universityconnect.service;

import universityconnect.domain.Discussion;
import universityconnect.domain.Event;
import universityconnect.domain.EventAttendance;
import universityconnect.dto.DiscussionDTO;
import universityconnect.dto.EventAttendanceDTO;

import java.util.List;

public interface EventAttendanceService {
    EventAttendanceDTO createEventAttendance(EventAttendanceDTO eventAttendanceDTO);
    EventAttendanceDTO getEventAttendanceById(Long id);
    List<EventAttendanceDTO> getAllEventAttendance();
    EventAttendanceDTO updateEventAttendance(Long id, EventAttendanceDTO eventAttendanceDTO);
    void deleteEventAttendance(Long id);
    EventAttendance findById(Long id);
}
