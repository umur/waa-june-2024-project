package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "discussions")
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;
    private String body;

    @ManyToOne
    private Student student;

    @ManyToOne
    private DiscussionCategory category;

    public void setStudent(Long studentId) {
        Student s = new Student();
        s.setId(studentId);

        this.student = s;
    }

}
