package edu.university_connect.model.contract.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StudentDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String year;

    private String major;

    LocalDateTime createdAt;

    String createdBy;

    LocalDateTime lastModifiedAt;

    String lastModifiedBy;
}
