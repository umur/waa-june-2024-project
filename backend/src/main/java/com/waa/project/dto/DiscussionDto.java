package com.waa.project.dto;

import lombok.Data;

@Data
public class DiscussionDto {

    private Long                  id;
    private String                title;
    private String                body;
    private String                studentCode;
    private DiscussionCategoryDto category;
    private Long                  category_id;
}
