package com.waa.project.dto.responses;

import com.waa.project.dto.requests.EventRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventsDto {
    private Long                  id;
    private String                firstName;
    private String                lastName;
    private String                email;
    private String                studentCode;
    private List<EventRequestDto> eventList;
}
