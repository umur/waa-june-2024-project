package edu.university_connect.service.event;

import edu.university_connect.domain.entity.Event;
import edu.university_connect.model.contract.dto.EventDto;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.event.EventRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    Page<EventDto> getAllByPage(Pageable pageable);

    EventDto getById(Long id);

    boolean delete(Long id);

    EventDto update(Long id, EventRequest eventRequest);

    EventDto create(EventRequest eventRequest);
    UserDto addAttendeeForEvent(Long id, Long userid);
    void rsvpForEvent(Long eventId, Long userId);
    Page<UserDto> getAllAttendeeByPage(Long eventId, Pageable pageableReq);

    Page<EventDto> getPageByUser(Long id, Pageable pageable);
}
