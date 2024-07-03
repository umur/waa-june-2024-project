package com.waa.project.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private String    name;
    private String    description;
    private LocalDate eventDate;
    private LocalTime eventTime;
}
