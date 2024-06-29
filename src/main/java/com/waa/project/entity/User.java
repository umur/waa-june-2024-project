package com.waa.project.entity;

import com.waa.project.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String gender;

    private boolean acccount_Status;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
