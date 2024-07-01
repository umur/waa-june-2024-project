package com.waa.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "students")
public class Student extends User {

    @Column(name = "student_code")
    @Size(min = 6, max = 6, message = "Student code should be 6 digits")
    private String studentCode;

    @Column(name = "academic_years")
    private String academicYears;

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
