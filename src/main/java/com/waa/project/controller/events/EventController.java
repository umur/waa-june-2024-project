package com.waa.project.controller.events;

import com.waa.project.dto.requests.EventDTO;
import com.waa.project.entity.Student;
import com.waa.project.service.EventService;
import com.waa.project.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventService eventService;
    private final UserService  userService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService  = userService;
    }

    @GetMapping("/events")
    public List<EventDTO> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/events/{id}")
    public EventDTO getEvent(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PostMapping("/admins/events")
    public EventDTO createEvent(@RequestBody EventDTO eventDTO) {
        return eventService.save(eventDTO);
    }

    @PutMapping("/admins/events/{id}")
    public EventDTO updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        return eventService.update(eventDTO, id);
    }

    @DeleteMapping("/admins/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
    }

    // Get all  attendees for an events by admin
    @GetMapping("/admins/events/{eventId}/attendees")
    public List<Student> getEventAttendees(@PathVariable Long eventId) {
        return  eventService.getAttendeesForEvent(eventId);
    }

    @GetMapping("/students/events/{studentId}/events")
    public List<EventDTO> getEventsByStudentId(@PathVariable Long studentId) {
        return eventService.getEventsByStudentId(studentId);
    }
    @PostMapping("/students/events/{eventId}/reservation")
    public EventDTO addEventReservation(@PathVariable Long eventId,
                                @AuthenticationPrincipal UserDetails
                                 userDetails) {

       var currentUser = userService.findByUsername(userDetails.getUsername());

       return eventService.addEventReservation(eventId, currentUser.getId());
    }

    @DeleteMapping("/students/events/{eventId}/reservation")
    public void removeEventReservation(@PathVariable Long eventId,@AuthenticationPrincipal UserDetails
            userDetails) {
        var currentUser = userService.findByUsername(userDetails.getUsername());
         eventService.removeEventReservation(eventId, currentUser.getId());
    }
}