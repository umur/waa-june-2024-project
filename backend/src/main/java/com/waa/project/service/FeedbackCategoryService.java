package com.waa.project.service;

import com.waa.project.dto.FeedbackCategoryDto;
import com.waa.project.dto.requests.FeedbackCategoryRequest;
import com.waa.project.dto.responses.FeedbackCategoryResponse;
import com.waa.project.entity.FeedbackCategory;

import java.util.List;

public interface FeedbackCategoryService {
    List<FeedbackCategoryDto> getAllCategories();

    FeedbackCategoryDto getCategory(Long feedId);

    FeedbackCategoryResponse save(FeedbackCategoryRequest category);

    String update(FeedbackCategoryDto category, Long fid);

    List<FeedbackCategory> delete(Long fid);
}
