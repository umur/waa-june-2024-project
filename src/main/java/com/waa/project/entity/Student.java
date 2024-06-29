package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
public class Student extends User{

    private String student_id;
    private String academicYear;
    private String picture;

    @ElementCollection
    @CollectionTable(name = "achievements", joinColumns = @JoinColumn(name = "student_id"))
    private Set<String> achievements;

    @ElementCollection
    @CollectionTable(name = "interests", joinColumns = @JoinColumn(name = "student_id"))
    private Set<String> interest;

    @ElementCollection
    @CollectionTable(name = "extra_activities", joinColumns = @JoinColumn(name = "student_id"))
    private Set<String> extra_activities;

    @ManyToOne
    private Major major;

    @ManyToMany(mappedBy = "attendedStudents")
    private List<Event> eventList;

    @Embedded
    private AuditData auditData;
}
