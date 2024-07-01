package com.waa.project.controller.events;

import com.waa.project.dto.requests.EventDTO;
import com.waa.project.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<EventDTO> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public EventDTO getEvent(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PostMapping
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.save(eventDTO);
    }

    @PutMapping("/{id}")
    public EventDTO updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        return eventService.update(eventDTO, id);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
    }

    @PostMapping("/{eventId}/attendees/{studentId}")
    public EventDTO addAttendee(@PathVariable Long eventId, @PathVariable Long studentId) {
        return null;
    }

    @DeleteMapping("/{eventId}/attendees/{studentId}")
    public EventDTO removeAttendee(@PathVariable Long eventId, @PathVariable Long studentId) {
        return null;
        }
}