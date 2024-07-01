package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDateTime dateTime;
    // determine how files will be stored
//    private String files;

//    @ManyToOne
//    private Admin createdByAdmin;

    @ManyToMany
    private List<Student> attendedStudents;

    @Embedded
    private AuditData auditData;

}
