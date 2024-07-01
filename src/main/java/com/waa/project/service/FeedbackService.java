package com.waa.project.service;

import com.waa.project.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    List<Feedback> getAllFeedbacks();

    Feedback getFeedback(Long feedId);

    String save(Feedback feedback);

    String update(Feedback feedback, Long fid);

    String delete(Long fid);
}
