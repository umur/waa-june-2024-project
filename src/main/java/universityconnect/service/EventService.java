package universityconnect.service;

import universityconnect.domain.Discussion;
import universityconnect.domain.Event;
import universityconnect.dto.DiscussionDTO;
import universityconnect.dto.EventDTO;

import java.util.List;

public interface EventService {
    EventDTO createEvent(EventDTO eventDTO);
    EventDTO getEventById(Long id);
    List<EventDTO> getAllEvent();
    EventDTO updateEventById(Long id, EventDTO eventDTO);
    void deleteEventById(Long id);
    Event findById(Long id);
}
