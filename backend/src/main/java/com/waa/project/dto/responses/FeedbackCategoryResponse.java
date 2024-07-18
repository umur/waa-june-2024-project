package com.waa.project.dto.responses;

import com.waa.project.entity.FeedbackCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackCategoryResponse {
    private Long id;
    private String name;
    private String description;
    private List<FeedbackCategory> categoryList;
}
