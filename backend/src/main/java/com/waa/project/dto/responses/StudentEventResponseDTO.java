package com.waa.project.dto.responses;

import com.waa.project.dto.requests.EventRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEventResponseDTO {
    private Long id;
    private String name;
    private String    description;
    private LocalDate eventDate;
    private LocalTime eventTime;
}
