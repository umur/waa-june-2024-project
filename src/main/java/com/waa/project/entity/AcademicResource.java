package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "academic_resources")
public class AcademicResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    private String body;
    private String file;

    @ManyToOne
    private AcademicResourceType resourceType;

    @ManyToOne
    private Course course;

    @Embedded
    private AuditData auditData;
}
