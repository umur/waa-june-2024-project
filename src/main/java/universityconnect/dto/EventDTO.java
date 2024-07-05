package universityconnect.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventDTO {
    private Long id;
    private String title;
    private String location;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private List<Long> eventAttendanceIds;
    private Long organizerId;

    public EventDTO() {}

    public EventDTO(Long id, String title, String location, LocalDateTime startDateTime, LocalDateTime endDateTime, List<Long> eventAttendanceIds, Long organizerId) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.eventAttendanceIds = eventAttendanceIds;
        this.organizerId = organizerId;
    }
}

