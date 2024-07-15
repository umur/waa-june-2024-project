package com.waa.project.dto;

import com.waa.project.entity.AcademicResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicResourceDto {

    private Long id;
    private String name;
    private String file;
    private AcademicResourceType resourceType;
}
