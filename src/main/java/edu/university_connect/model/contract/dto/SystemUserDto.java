package edu.university_connect.model.contract.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SystemUserDto {
    private Long id;

    private String username;

    private String email;

    private boolean enabled;

    LocalDateTime createdAt;

    String createdBy;

    LocalDateTime lastModifiedAt;

    String lastModifiedBy;

}
