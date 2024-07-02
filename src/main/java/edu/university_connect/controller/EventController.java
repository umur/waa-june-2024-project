package edu.university_connect.controller;

import edu.university_connect.model.contract.dto.EventDto;
import edu.university_connect.model.contract.request.event.EventRequest;
import edu.university_connect.model.contract.response.ApiResponse;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.service.MessagingService;
import edu.university_connect.service.event.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final MessagingService messagingService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EventDto>>> getAll() {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, new String[]{"event"});
        return ResponseEntity.ok(new ApiResponse<List<EventDto>>(responseMessage, eventService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EventDto>> getById(@PathVariable Long id) {
        String responseMessage = messagingService
                .getResponseMessage(AppStatusCode.S20001, "event");
        return ResponseEntity.ok(new ApiResponse<>(responseMessage, eventService.getById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Long id) {
        boolean response = eventService.delete(id);
        String message = messagingService.getResponseMessage(AppStatusCode.S20005,
                new String[]{"event"});
        return ResponseEntity.ok(new ApiResponse<Boolean>(message, response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EventDto>> update(@Valid @RequestBody EventRequest eventRequest,
                                                        @PathVariable Long id) {
        EventDto updatedEvent = eventService.update(id, eventRequest);
        String responseMessage = messagingService.getResponseMessage(AppStatusCode.S20004,
                new String[]{"event"});
        return ResponseEntity.ok(new ApiResponse<>(responseMessage, updatedEvent));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EventDto>> create(@Valid @RequestBody EventRequest eventRequest) {
        EventDto eventDto = eventService.create(eventRequest);
        String message = messagingService.getResponseMessage(AppStatusCode.S20002, new String[]{"event"});
        return ResponseEntity.ok(new ApiResponse<EventDto>(message, eventDto));
    }
}
