package universityconnect.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import universityconnect.domain.Event;
import universityconnect.domain.EventAttendance;
import universityconnect.dto.EventDTO;
import universityconnect.exception.ResourceNotFoundException;
import universityconnect.mapper.EventMapper;
import universityconnect.repository.EventRepository;
import universityconnect.service.EventAttendanceService;
import universityconnect.service.EventService;
import universityconnect.service.UserService;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final UserService userService;
    private final EventAttendanceService attendanceService;

    @Override
    public EventDTO createEvent(EventDTO eventDTO) {
        Event event = eventMapper.eventDTOToEvent(eventDTO);
        event.setOrganizer(userService.findById(eventDTO.getOrganizerId()));
        event.setEventAttendances(new ArrayList<>());
        Event savedEvent = eventRepository.save(event);
        return eventMapper.eventToEventDTO(savedEvent);
    }

    @Override
    public EventDTO getEventById(Long id) {
        Event result = eventRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event not found with id: "+id));
        return eventMapper.eventToEventDTO(result);
    }

    @Override
    public List<EventDTO> getAllEvent() {
        return eventRepository.findAll().stream().map(eventMapper::eventToEventDTO).toList();
    }

    @Override
    public EventDTO updateEventById(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event not found with id: "+id));
        event.setOrganizer(userService.findById(eventDTO.getOrganizerId()));
        event.setTitle(eventDTO.getTitle());
        event.setLocation(eventDTO.getLocation());
        event.setStartDateTime(eventDTO.getStartDateTime());
        event.setEndDateTime(eventDTO.getEndDateTime());
        List<EventAttendance> attendanceDTOS = new ArrayList<>();
        eventDTO.getEventAttendanceIds().forEach(attId->attendanceDTOS.add(attendanceService.findById(attId)));
        event.setEventAttendances(attendanceDTOS);
        Event savedEvent = eventRepository.save(event);
        return eventMapper.eventToEventDTO(savedEvent);
    }

    @Override
    public void deleteEventById(Long id) {
        if(eventRepository.existsById(id)){
            eventRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Event not found with id: "+id);
        }
    }

    @Override
    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Event not found with id: "+id));
    }
}
