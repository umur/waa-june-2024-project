package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.Event;
import universityconnect.domain.EventAttendance;
import universityconnect.domain.Student;
import universityconnect.dto.EventAttendanceDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.EventAttendanceMapper;
import universityconnect.repository.EventAttendanceRepository;
import universityconnect.repository.EventRepository;
import universityconnect.repository.StudentRepository;
import universityconnect.service.EventAttendanceService;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventAttendanceServiceImpl implements EventAttendanceService {
    private final EventAttendanceRepository attendanceRepository;
    private final EventAttendanceMapper attendanceMapper;
    private final StudentRepository studentRepository;
    private final EventRepository eventRepository;
    @Override
    public EventAttendanceDTO createEventAttendance(EventAttendanceDTO eventAttendanceDTO) {
        EventAttendance attendance = attendanceMapper.eventAttendanceDTOToEventAttendance(eventAttendanceDTO);
        Student student = studentRepository.findById(eventAttendanceDTO.getStudentId()).orElseThrow(()->new ResourceNotFoundException("Student not found with id: "+eventAttendanceDTO.getStudentId()));
        attendance.setStudent(student);
        Event event = eventRepository.findById(eventAttendanceDTO.getEventId()).orElseThrow(()->new ResourceNotFoundException("Event attendance not found with id: "+eventAttendanceDTO.getEventId()));
        attendance.setEvent(event);
        EventAttendance savedAttendance = attendanceRepository.save(attendance);
        return attendanceMapper.eventAttendanceToEventAttendanceDTO(savedAttendance);
    }

    @Override
    public EventAttendanceDTO getEventAttendanceById(Long id) {
        EventAttendance attendance = attendanceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event attendance not found with id"+id));
        return attendanceMapper.eventAttendanceToEventAttendanceDTO(attendance);
    }

    @Override
    public List<EventAttendanceDTO> getAllEventAttendance() {
        return attendanceRepository.findAll().stream().map(attendanceMapper::eventAttendanceToEventAttendanceDTO).toList();
    }

    @Override
    public EventAttendanceDTO updateEventAttendance(Long id, EventAttendanceDTO eventAttendanceDTO) {
        EventAttendance attendance = attendanceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event attendance not found with id: "+id));
        attendance.setResponse(eventAttendanceDTO.isResponse());
        attendance.setCheckInTime(eventAttendanceDTO.getCheckInTime());
        attendance.setCheckOutTime(eventAttendanceDTO.getCheckOutTime());
        Student student = studentRepository.findById(eventAttendanceDTO.getStudentId()).orElseThrow(()->new ResourceNotFoundException("Student not found with id: "+eventAttendanceDTO.getStudentId()));
        attendance.setStudent(student);
        attendance.setStudent(student);
        return attendanceMapper.eventAttendanceToEventAttendanceDTO(attendance);
    }

    @Override
    public void deleteEventAttendance(Long id) {
        if(attendanceRepository.existsById(id)){
            attendanceRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Event Attendance not found with id: "+id);
        }
    }

    @Override
    public EventAttendance findById(Long id) {
        return attendanceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event attendance not found with id: "+id));
    }
}
