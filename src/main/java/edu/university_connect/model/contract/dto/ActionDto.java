package edu.university_connect.model.contract.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActionDto {
    private Long id;

    private String name;

    private String code;

    private String description;

    LocalDateTime createdAt;

    String createdBy;

    LocalDateTime lastModifiedAt;

    String lastModifiedBy;

    private boolean enabled;
}
