package com.waa.project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "feedback_categories")
public class FeedbackCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 255, message = "Category name must be minimum of 2 characters long")
    private String name;

    @Column(name = "description")
    @Size(min = 2, max = 255, message = "Description must be minimum of 2 characters long")
    private String description;

    @Embedded
    private AuditData auditData;
}
