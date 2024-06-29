package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String body;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String files;

//    @ManyToOne
//    private Admin createdByAdmin;

    @ManyToOne
    private DiscussionCategory category;

    @ManyToMany
    private List<Student> attendedStudents;

    @Embedded
    private AuditData auditData;

}
