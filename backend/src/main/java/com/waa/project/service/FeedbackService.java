package com.waa.project.service;

import com.waa.project.dto.FeedbackDto;
import com.waa.project.dto.requests.FeedbackRequest;
import com.waa.project.dto.responses.FeedbackResponse;
import com.waa.project.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();

    FeedbackDto getFeedback(Long feedId);

    FeedbackResponse save(FeedbackRequest feedbackRequest, Long userId);

    FeedbackResponse update(FeedbackRequest feedbackRequest, Long fid, Long userId);

    List<Feedback> delete(Long fid);

    List<FeedbackDto> findFeedbackByCategory(Long id);

    List<Object[]> findFeedbackByCategoryCount();

    List<FeedbackDto> search(String search);

    List<FeedbackDto> findByTitleAndId(String title, Long id);
}
