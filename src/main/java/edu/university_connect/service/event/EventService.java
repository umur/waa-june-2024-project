package edu.university_connect.service.event;

import edu.university_connect.domain.entity.Event;
import edu.university_connect.model.contract.dto.EventDto;
import edu.university_connect.model.contract.request.event.EventRequest;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<EventDto> getAll();

    EventDto getById(Long id);

    boolean delete(Long id);

    EventDto update(Long id, EventRequest eventRequest);

    EventDto create(EventRequest eventRequest);

    void rsvpForEvent(Long eventId, Long userId);
}
