package com.waa.project.controller.events;

import com.waa.project.dto.requests.EventRequestDto;
import com.waa.project.dto.responses.EventResponseDto;
import com.waa.project.dto.responses.EventsDto;
import com.waa.project.dto.responses.StudentEventResponseDTO;
import com.waa.project.service.EventService;
import com.waa.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        return new ResponseEntity<>(eventService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<EventResponseDto> getEvent(@PathVariable Long id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/admins/events")
    public ResponseEntity<EventRequestDto> createEvent(@RequestBody EventRequestDto eventRequestDTO) {
        return new ResponseEntity<> (eventService.save(eventRequestDTO), HttpStatus.CREATED);
    }

    @PutMapping("/admins/events/{id}")
    public ResponseEntity<EventRequestDto> updateEvent(@PathVariable Long id, @RequestBody EventRequestDto eventRequestDTO) {
        return new ResponseEntity<> (eventService.update(eventRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/admins/events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long id) {
        eventService.deleteById(id);
        return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
    }

    // Get all  attendees for an events by admin
    @GetMapping("/admins/events/{eventId}/attendees")
    public ResponseEntity<List<EventsDto>> getEventAttendees(@PathVariable Long eventId) {
        return new ResponseEntity<>(eventService.getAttendeesForEvent(eventId), HttpStatus.OK);
    }

    @GetMapping("/students/events/{studentId}/events")
    public ResponseEntity<List<StudentEventResponseDTO> >getEventsByStudentId(@PathVariable Long studentId) {
        return new ResponseEntity<>(eventService.getEventsByStudentId(studentId), HttpStatus.OK);
    }
    @PostMapping("/students/events/{eventId}/reservation")
    public ResponseEntity<EventRequestDto> addEventReservation(@PathVariable Long eventId,
                                               @AuthenticationPrincipal UserDetails
                                 userDetails) {

       var currentUser = userService.findByUsername(userDetails.getUsername());

       return new ResponseEntity<>(eventService.addEventReservation(eventId, currentUser.getId()), HttpStatus.CREATED);
    }

    @DeleteMapping("/students/events/{eventId}/reservation")
    public ResponseEntity<String> removeEventReservation(@PathVariable Long eventId,@AuthenticationPrincipal UserDetails
            userDetails) {
        var currentUser = userService.findByUsername(userDetails.getUsername());
         eventService.removeEventReservation(eventId, currentUser.getId());
         return new ResponseEntity<>("Reservation deleted successfully", HttpStatus.OK);
    }
}