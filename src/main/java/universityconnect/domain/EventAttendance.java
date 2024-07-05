package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class EventAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private boolean isResponse;
    @ManyToOne
    private Event event;

    @ManyToOne
    private Student student;

    public EventAttendance() {}

}
