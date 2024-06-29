package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;

    @ManyToOne
    private Student student;

    @ManyToOne
    private DiscussionCategory category;

}
