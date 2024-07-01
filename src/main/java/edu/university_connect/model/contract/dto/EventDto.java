package edu.university_connect.model.contract.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventDto {
    private Long id;
    private String name;
    private String description;
    private String location;
    private LocalDate date;
    LocalDateTime createdAt;

    String createdBy;

    LocalDateTime lastModifiedAt;

    String lastModifiedBy;
}
