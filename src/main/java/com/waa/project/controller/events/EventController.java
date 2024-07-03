package com.waa.project.controller.events;

import com.waa.project.dto.requests.EventRequestDto;
import com.waa.project.dto.responses.EventResponseDto;
import com.waa.project.dto.responses.EventsDto;
import com.waa.project.dto.responses.StudentEventResponseDTO;
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
    public List<EventResponseDto> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/events/{id}")
    public EventResponseDto getEvent(@PathVariable Long id) {
        return eventService.findById(id);
    }

    @PostMapping("/admins/events")
    public EventRequestDto createEvent(@RequestBody EventRequestDto eventRequestDTO) {
        return eventService.save(eventRequestDTO);
    }

    @PutMapping("/admins/events/{id}")
    public EventRequestDto updateEvent(@PathVariable Long id, @RequestBody EventRequestDto eventRequestDTO) {
        return eventService.update(eventRequestDTO, id);
    }

    @DeleteMapping("/admins/events/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
    }

    // Get all  attendees for an events by admin
    @GetMapping("/admins/events/{eventId}/attendees")
    public List<EventsDto> getEventAttendees(@PathVariable Long eventId) {
        return  eventService.getAttendeesForEvent(eventId);
    }

    @GetMapping("/students/events/{studentId}/events")
    public List<StudentEventResponseDTO> getEventsByStudentId(@PathVariable Long studentId) {
        return eventService.getEventsByStudentId(studentId);
    }
    @PostMapping("/students/events/{eventId}/reservation")
    public EventRequestDto addEventReservation(@PathVariable Long eventId,
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