package miu.waa.project1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
public class Event {
    @Id
    private Long id;

    private String name;
    private String description;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;

    @OneToMany
    private List<User> attendees;
}
