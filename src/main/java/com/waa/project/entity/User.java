package com.waa.project.entity;

import com.waa.project.enums.AccountStatusType;
import com.waa.project.enums.GenderType;
import com.waa.project.enums.RoleType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @Size(min = 3, max = 10, message = "Name must be between 3 and 10 characters long")
    private String username;

    @Column(name = "password")
    @Size(min = 5, max = 15, message = "Name must be between 5 and 15 characters long")
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "gender_type")
    @Enumerated(EnumType.STRING)
    private GenderType genderType;

    @Column(name = "account_status")
    @Enumerated(EnumType.STRING)
    private AccountStatusType accountStatus;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
