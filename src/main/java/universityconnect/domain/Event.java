package universityconnect.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String location;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    @OneToMany
    @JoinColumn(name = "event_id")
    private List<EventAttendance> eventAttendances;

    @ManyToOne
    private User organizer;

    public Event() {}

}
