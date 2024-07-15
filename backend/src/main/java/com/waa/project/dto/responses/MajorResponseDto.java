package com.waa.project.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorResponseDto {
    private Long   id;
    private String name;
    private String description;
}
