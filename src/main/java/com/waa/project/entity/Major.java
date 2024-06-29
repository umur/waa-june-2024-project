package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Major {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Embedded
    private AuditData auditData;
}
