package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "feedbacks")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body")
    private String body;

    @ManyToOne
    private FeedbackCategory category;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private Student student;

    @Column(name = "posted_date", nullable = false, updatable = false)
    private LocalDateTime postedDate;

    @PrePersist
    protected void onCreate() {
        postedDate = LocalDateTime.now();
    }
}
