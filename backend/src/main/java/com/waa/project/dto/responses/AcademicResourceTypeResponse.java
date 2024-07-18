package com.waa.project.dto.responses;

import com.waa.project.entity.AcademicResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcademicResourceTypeResponse {
    private Long id;
    private String name;
    private List<AcademicResourceType> academicResourceTypeList;
}

