package edu.university_connect.model.entity;

import edu.university_connect.model.entity.meta.MetaData;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
public class Event extends MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;

    @ManyToMany
    private List<User> attendees;
}
