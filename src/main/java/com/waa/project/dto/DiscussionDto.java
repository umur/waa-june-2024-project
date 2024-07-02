package com.waa.project.dto;

import com.waa.project.entity.Student;
import lombok.Data;

@Data
public class DiscussionDto {

    private Long                  id;
    private String                title;
    private String                body;
    private Student               student;
    private DiscussionCategoryDto category;
}
