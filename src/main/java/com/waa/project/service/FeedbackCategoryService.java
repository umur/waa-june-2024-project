package com.waa.project.service;

import com.waa.project.dto.FeedbackCategoryDto;

import java.util.List;

public interface FeedbackCategoryService {
    List<FeedbackCategoryDto> getAllCategories();

    FeedbackCategoryDto getCategory(Long feedId);

    String save(FeedbackCategoryDto category);

    String update(FeedbackCategoryDto category, Long fid);

    String delete(Long fid);
}
