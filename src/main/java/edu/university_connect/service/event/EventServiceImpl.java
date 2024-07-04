package edu.university_connect.service.event;

import edu.university_connect.domain.entity.Event;
import edu.university_connect.domain.entity.User;
import edu.university_connect.exception.ServiceException;
import edu.university_connect.mapper.EventDtoMapper;
import edu.university_connect.model.contract.dto.EventDto;
import edu.university_connect.model.contract.request.event.EventRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.repository.EventRepository;
import edu.university_connect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    public List<EventDto> getAll() {
        return eventRepository.findAll()
                .stream()
                .map(EventDtoMapper.MAPPER::entityToDto)
                .toList();
    }

    @Override
    public EventDto getById(Long id) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "event");
        }
        return EventDtoMapper.MAPPER.entityToDto(eventOpt.get());
    }

    @Override
    public boolean delete(Long id) {
        if (Objects.nonNull(getById(id))) {
            eventRepository.deleteById(id);
        }
        return false;
    }

    @Override
    public EventDto update(Long id, EventRequest eventRequest) {
        Optional<Event> eventOpt = eventRepository.findById(id);
        if (eventOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "event");
        }
        Event event = eventOpt.get();
        event.setName(eventRequest.getName());
        event.setDescription(eventRequest.getDescription());
        event.setDate(eventRequest.getDate());
        event.setLocation(eventRequest.getLocation());
        Event savedEvent = eventRepository.save(event);
        return EventDtoMapper.MAPPER.entityToDto(savedEvent);
    }

    @Override
    public EventDto create(EventRequest eventRequest) {
        Event event = EventDtoMapper.MAPPER.dtoToEntity(eventRequest);
        Event savedEvent = eventRepository.save(event);
        return EventDtoMapper.MAPPER.entityToDto(savedEvent);
    }

    @Override
    public void rsvpForEvent(Long eventId, Long userId) {
        Optional<Event> eventOpt = eventRepository.findById(eventId);
        Optional<User> userOpt = userRepository.findById(userId);
        if(eventOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "event");
        }
        if(userOpt.isEmpty()) {
            throw ServiceException.of(AppStatusCode.E40000, "user");
        }
        Event event = eventOpt.get();
        event.addAttendee(userOpt.get());
        eventRepository.save(event);
    }
}
