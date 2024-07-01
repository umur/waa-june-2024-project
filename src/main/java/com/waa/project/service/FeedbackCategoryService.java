package com.waa.project.service;

import com.waa.project.entity.FeedbackCategory;

import java.util.List;

public interface FeedbackCategoryService {
    List<FeedbackCategory> getAllCategories();

    FeedbackCategory getCategory(Long feedId);

    String save(FeedbackCategory category);

    String update(FeedbackCategory category, Long fid);

    String delete(Long fid);
}
