package universityconnect.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import universityconnect.dto.EventAttendanceDTO;
import universityconnect.service.EventAttendanceService;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/events/attendances")
@RequiredArgsConstructor
@CrossOrigin
public class EventAttendanceController {
    private final EventAttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<EventAttendanceDTO> createEventAttendance(@RequestBody EventAttendanceDTO eventAttendanceDTO) {
        EventAttendanceDTO createdEventAttendance = attendanceService.createEventAttendance(eventAttendanceDTO);
        return new ResponseEntity<>(createdEventAttendance, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventAttendanceDTO> getEventAttendanceById(@PathVariable Long id) {
        EventAttendanceDTO eventAttendanceDTO = attendanceService.getEventAttendanceById(id);
        return new ResponseEntity<>(eventAttendanceDTO,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventAttendanceDTO>> getAllEventAttendance() {
        List<EventAttendanceDTO> eventAttendanceDTOS = attendanceService.getAllEventAttendance();
        return ResponseEntity.ok(eventAttendanceDTOS);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventAttendanceDTO> updateEventAttendanceById(@PathVariable Long id, @RequestBody EventAttendanceDTO eventAttendanceDTO) {
        EventAttendanceDTO updatedEventAttendance = attendanceService.updateEventAttendance(id,eventAttendanceDTO);
        return ResponseEntity.ok(updatedEventAttendance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventAttendanceById(@PathVariable Long id) {
        attendanceService.deleteEventAttendance(id);
        return ResponseEntity.noContent().build();
    }

}
