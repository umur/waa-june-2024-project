package com.waa.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicResourceRequest {
    private String name;
    private File file;
    private Long resourceId;
}
