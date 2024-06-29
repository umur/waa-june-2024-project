package universityconnect.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class EventAttendanceDTO {
    private Long id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private boolean isResponse;
    private Long studentId;

    public EventAttendanceDTO() {}

    public EventAttendanceDTO(Long id, LocalDateTime checkInTime, LocalDateTime checkOutTime, boolean isResponse, Long studentId) {
        this.id = id;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.isResponse = isResponse;
        this.studentId = studentId;
    }
}

