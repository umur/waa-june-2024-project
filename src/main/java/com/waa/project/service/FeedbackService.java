package com.waa.project.service;

import com.waa.project.dto.FeedbackDto;

import java.util.List;

public interface FeedbackService {
    List<FeedbackDto> getAllFeedbacks();

    FeedbackDto getFeedback(Long feedId);

    String save(FeedbackDto feedback, Long userId);

    String update(FeedbackDto feedback, Long fid, Long userId);

    String delete(Long fid);

    List<FeedbackDto> findFeedbackByCategory(Long id);

    List<Object[]> findFeedbackByCategoryCount();
}