package miu.waa.project1.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import miu.waa.project1.dto.EventDTO;
import miu.waa.project1.model.Event;
import miu.waa.project1.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Getter
@Setter
@RequestMapping("/api/v1/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getEvent(id);
    }

    @PostMapping
    public Event createEvent(@RequestBody EventDTO event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody EventDTO event) {
        eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping("/{id}/attend/{userId}")
    public void attendEvent(@PathVariable Long id, @PathVariable Long userId) {
        eventService.attendEvent(id, userId);
    }

    @PutMapping("/{id}/cancel/{userId}")
    public void cancelAttendance(@PathVariable Long id, @PathVariable Long userId) {
        eventService.cancelAttendance(id, userId);
    }

    @GetMapping("/{id}/attendees")
    public int getAttendees(@PathVariable Long id) {
        return eventService.getAttendees(id);
    }
}
