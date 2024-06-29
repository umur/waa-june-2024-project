package edu.university_connect.model.contract.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class SystemRoleDto {
    private Long id;

    private String name;

    private String code;

    private Set<String> actions;

    private String description;

    LocalDateTime createdAt;

    String createdBy;

    LocalDateTime lastModifiedAt;

    String lastModifiedBy;

    private boolean enabled;
}
