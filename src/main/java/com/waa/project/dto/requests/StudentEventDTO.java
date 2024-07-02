package com.waa.project.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEventDTO {
    private Long           id;
    private String         username;
    private String         password;
    private String         firstName;
    private String         lastName;
    private String         email;
    private String         studentCode;
    private List<EventDTO> eventList;

}
