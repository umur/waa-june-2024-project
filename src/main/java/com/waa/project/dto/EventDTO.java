package com.waa.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long          id;
    private String        title;
    private String        description;
    private LocalDateTime dateTime;
//    private List<Long>    attendeeIds;
}
