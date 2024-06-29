package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AcademicResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String body;
    // files

    @ManyToOne
    private AcademicResourceType resourceType;

    @ManyToOne
    private Course course;

    @Embedded
    private AuditData auditData;
}
