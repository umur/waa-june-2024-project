package com.waa.project.dto.responses;

import com.waa.project.entity.AcademicResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicResourceResponse {

    private Long id;
    private String name;
    private String body;
    private File file;
    private AcademicResourceType resourceType;
}
