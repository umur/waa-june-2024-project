package miu.waa.project1.service.impl;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.model.Event;
import miu.waa.project1.model.User;
import miu.waa.project1.repository.EventRepository;
import miu.waa.project1.repository.UserRepository;
import miu.waa.project1.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void updateEvent(Long id, Event e) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setName(e.getName());
            event.setDescription(e.getDescription());
            event.setStartDate(e.getStartDate());
            event.setEndDate(e.getEndDate());
            event.setLocation(e.getLocation());
            event.setCapacity(e.getCapacity());
        }
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    @Override
    public void attendEvent(Long id, Long userId) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                event.getAttendees().add(user);
            }
        }
    }

    @Override
    public void cancelAttendance(Long id, Long userId) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                event.getAttendees().remove(user);
            }
        }
    }

    @Override
    public int getAttendees(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            return event.getAttendees().size();
        }
        return 0;
    }

    @Override
    public Event getEvent(Long id) {
        return eventRepository.findById(id).orElse(null);
    }
}
