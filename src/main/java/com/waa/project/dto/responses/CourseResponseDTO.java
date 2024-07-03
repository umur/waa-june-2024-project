package com.waa.project.dto.responses;

import com.waa.project.dto.MajorDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    private Long id;
    private String name;
    private String code;
    private MajorDto major;
}
