package edu.university_connect.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String year;
    private String major;

    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id",nullable = true)
    @JoinColumn(name = "user_id")
    private User user;
}
