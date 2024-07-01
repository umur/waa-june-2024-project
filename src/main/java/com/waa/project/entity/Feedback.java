package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    private String title;
    private String body;

    @ManyToOne
    private FeedbackCategory category;

    @ManyToOne
    private Student student;
}
