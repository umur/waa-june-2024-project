package com.waa.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Admin extends User{


    @Embedded
    private AuditData auditData;
}
