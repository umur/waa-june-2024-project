package com.waa.project.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponseDto {
    private Long                          id;
    private String                        name;
    private String                        description;
    private LocalDate                     eventDate;
    private LocalTime                     eventTime;
    private List<EventStudentResponseDto> attendedStudents;
}
