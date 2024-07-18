package miu.waa.project1.service.impl;

import lombok.RequiredArgsConstructor;
import miu.waa.project1.dto.EventDTO;
import miu.waa.project1.model.Event;
import miu.waa.project1.model.User;
import miu.waa.project1.repository.EventRepository;
import miu.waa.project1.repository.UserRepository;
import miu.waa.project1.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    @Override
    public Event createEvent(EventDTO e) {
        Event event = new Event();
        event.setLocation(e.getLocation());
        event.setName(e.getName());
        event.setCapacity(e.getCapacity());
        event.setDescription(e.getDescription());
        event.setStartDate(e.getStartDate());
        event.setEndDate(e.getEndDate());
        return eventRepository.save(event);
    }

    @Override
    public void updateEvent(Long id, EventDTO e) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
//            LocalDate date = LocalDate.of(e.getEndDate().getYear(), e.getEndDate().getMonth(), e.getEndDate().getDayOfMonth());

            event.setName(e.getName());
            event.setDescription(e.getDescription());
            event.setStartDate(e.getStartDate());
            event.setEndDate(e.getEndDate());
            event.setLocation(e.getLocation());
            event.setCapacity(e.getCapacity());
            event.setStartDate(LocalDate.of(e.getStartDate().getYear(), e.getStartDate().getMonth(), e.getStartDate().getDayOfMonth()));
            event.setEndDate(LocalDate.of(e.getEndDate().getYear(), e.getEndDate().getMonth(), e.getEndDate().getDayOfMonth()));
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
