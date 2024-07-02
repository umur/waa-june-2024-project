package com.waa.project.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
@Table(name = "admins")
public class Admin extends User {


    @Embedded
    private AuditData auditData;
}
