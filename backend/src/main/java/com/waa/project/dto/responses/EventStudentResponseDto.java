package com.waa.project.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventStudentResponseDto {
    private Long  id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String studentCode;
}
