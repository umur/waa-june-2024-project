package edu.university_connect.controller;

import edu.university_connect.config.ContextUser;
import edu.university_connect.model.contract.dto.CategoryDto;
import edu.university_connect.model.contract.dto.EventDto;
import edu.university_connect.model.contract.dto.UserDto;
import edu.university_connect.model.contract.request.event.EventRequest;
import edu.university_connect.model.contract.request.user.AttendenceRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.event.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final MessagingService messagingService;
    private final ContextUser contextUser;

    @GetMapping
    @PreAuthorize("hasAuthority('view_event_list')")
    public ResponseEntity<ApiResponse<List<EventDto>>> getAll() {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, new String[]{"event"});
        return ResponseEntity.ok(new ApiResponse<List<EventDto>>(responseMessage, eventService.getAll()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('view_event')")
    public ResponseEntity<ApiResponse<EventDto>> getById(@PathVariable Long id) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "event");
        return ResponseEntity.ok(new ApiResponse<>(responseMessage, eventService.getById(id)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('delete_event')")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response = eventService.delete(id);
        String message = messagingService.getResponseMessage(AppStatusCode.S20005,
                new String[]{"event"});
        return ResponseEntity.ok(new ApiResponse<Boolean>(message, response));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('modify_event')")
    public ResponseEntity<ApiResponse<EventDto>> update(@Valid @RequestBody EventRequest eventRequest,
                                                        @PathVariable Long id) {
        EventDto updatedEvent = eventService.update(id, eventRequest);
        String responseMessage = messagingService.getResponseMessage(AppStatusCode.S20004,
                new String[]{"event"});
        return ResponseEntity.ok(new ApiResponse<>(responseMessage, updatedEvent));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_event')")
    public ResponseEntity<ApiResponse<EventDto>> create(@Valid @RequestBody EventRequest eventRequest) {
        EventDto eventDto = eventService.create(eventRequest);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, new String[]{"event"});
        return ResponseEntity.ok(new ApiResponse<EventDto>(message, eventDto));
    }

    @PostMapping("/{id}/rsvp")
    @PreAuthorize("hasAuthority('create_event_rsvp')")
    public ResponseEntity<ApiResponse<Boolean>> rsvpForEvent(@Valid @PathVariable Long id) {
        Long userId = contextUser.getLoginUser().getId();
        eventService.rsvpForEvent(id, userId);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, new String[]{"event rsvp"});
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(message, true);
        apiResponse.setStatus(true);
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("{id}/attendee")
    @PreAuthorize("hasAuthority('create_event_attendance')")
    public ResponseEntity<ApiResponse<Boolean>> addEventAttendee(@Valid @PathVariable Long id,
                                                                 @RequestBody AttendenceRequest attendenceRequest) {
        UserDto userDto = eventService.addAttendeeForEvent(id, attendenceRequest.getUserId());
        String uname = userDto.getUsername();
        String message = messagingService.getResponseMessage(
                AppStatusCode.S20002, new String[]{"attendee ", uname});
        ApiResponse<Boolean> apiResponse = new ApiResponse<>(message, true);
        apiResponse.setStatus(true);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}/attendees")
    @PreAuthorize("hasAuthority('view_event_attendance_list')")
    public ResponseEntity<ApiResponse<Page<UserDto>>> eventRsvpAttendeeByPage(
            @PathVariable Long id,
            Pageable pageableReq) {
        Pageable pageable = PageRequest.of(pageableReq.getPageNumber() > 0 ? pageableReq.getPageNumber() - 1 : 0,
                pageableReq.getPageSize(),
                pageableReq.getSort());

        Page<UserDto> allRsvpResponder = eventService.getAllAttendeeByPage(id, pageable);
        ApiResponse<Page<UserDto>> apiResponse = new ApiResponse<Page<UserDto>>();
        apiResponse.setResponseData(allRsvpResponder);
        apiResponse.setMessage(messagingService.getResponseMessage(AppStatusCode.S20001, new String[]{"category"}));
        return ResponseEntity.ok(apiResponse);
    }
}
