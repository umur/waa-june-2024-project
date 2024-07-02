package edu.university_connect.model.contract.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private Long id;
    private String content;;
    LocalDateTime createdAt;
    String createdBy;
    LocalDateTime lastModifiedAt;
    String lastModifiedBy;
}
