package miu.waa.project1.service;

import miu.waa.project1.model.Event;

import java.util.List;

public interface EventService {
    Event createEvent(Event event);
    void updateEvent(Long id, Event event);
    void deleteEvent(Long id);
    List<Event> getEvents();
    void attendEvent(Long id, Long userId);
    void cancelAttendance(Long id, Long userId);
    int getAttendees(Long id);
    Event getEvent(Long id);
}
