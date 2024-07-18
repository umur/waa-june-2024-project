package com.waa.project.dto;

import com.waa.project.contracts.StudentResponse;
import lombok.Data;

@Data
public class DiscussionDto {

    private Long                  id;
    private String                title;
    private String                body;
    private StudentInfo student;
    private DiscussionCategoryDto category;
    private Long                  category_id;
    private boolean own;
}
