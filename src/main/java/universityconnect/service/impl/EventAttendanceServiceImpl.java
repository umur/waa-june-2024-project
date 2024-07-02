package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.EventAttendance;
import universityconnect.domain.User;
import universityconnect.dto.EventAttendanceDTO;
import universityconnect.mapper.EventAttendanceMapper;
import universityconnect.repository.EventAttendanceRepository;
import universityconnect.service.EventAttendanceService;
import universityconnect.service.UserService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventAttendanceServiceImpl implements EventAttendanceService {
    private final EventAttendanceRepository attendanceRepository;
    private final EventAttendanceMapper attendanceMapper;
    @Override
    public EventAttendanceDTO createEventAttendance(EventAttendanceDTO eventAttendanceDTO) {
        return null;
    }

    @Override
    public EventAttendanceDTO getEventAttendanceById(Long id) {
        return null;
    }

    @Override
    public List<EventAttendanceDTO> getAllEventAttendance() {
        return null;
    }

    @Override
    public EventAttendanceDTO updateEventAttendance(Long id, EventAttendanceDTO eventAttendanceDTO) {
        return null;
    }

    @Override
    public void deleteEventAttendance(Long id) {

    }

    @Override
    public EventAttendance findById(Long id) {
        return null;
    }
}
