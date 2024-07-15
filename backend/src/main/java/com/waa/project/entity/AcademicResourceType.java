package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "academic_resource_types")
public class AcademicResourceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private AuditData auditData;
}
